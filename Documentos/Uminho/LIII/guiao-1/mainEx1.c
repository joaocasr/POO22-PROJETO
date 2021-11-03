#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "types.h"

int getIntLen (int x)
{
    int r = 1;
    while (x>=10)
    {
      r++;
      x=x/10;
    }

    return r;
}

int check(char *validated)
{
    int valint = atoi(validated);  //converte em int
    
    if (strcmp(validated,"0")==0 || (valint>0 && getIntLen(valint)==strlen(validated))) return 1;

    return 0;
}

int find_errors(char *buff,int n)
{
    int error=0, len=strlen(buff)-2;
    if(strstr(buff,";;")!=NULL) error++;    //espaço vazio no meio da string
    if(n!=2 && buff[len]==';') error++;     //espaço vazio no final da string
    if(buff[0]==';') error++;               //espaço vazio no inicio da string
    return error;
}

int inArray(int elem, int array[], int tam)
{
    int i=0;
    while(i<tam && array[i]!=elem) i++;
    if(i==tam) return 0;
    return 1; // o elem existe
}

int check_Time(char* time) 
{
    int month_31[]={1,3,5,7,8,10,12};
    int month_30[]={4,6,9,11};
    int r=1, year=0, month=0, day=0, hour=0, minute=0, second=0;

    char line[strlen(time)+1];
    strcpy(line,time);
    
    if(line[19]!='\0' || line[4]!='-' || line[7]!='-' || sscanf(time,"%d-%d-%d %d:%d:%d", &year, &month, &day, &hour, &minute, &second)!=6) r=0;
    if(year<2005 || year>2021) r=0;
    else if(year==2005 && month<4) r=0;
    else if(year==2005 && month==4 && day<7) r=0;
    else if(month>12 || hour>24 || minute>60 || second>60) r=0;
    else if(year % 4 == 0 && month == 2 && day>29) r=0; // ano bissexto
    else if(year % 4 !=0 && month == 2 && day>28) r=0;
    else if(inArray(month,month_31,7) && day>31) r=0;
    else if(inArray(month,month_30,4) && day>30) r=0;
    else r=1;

    return r;
}


int check_list(int follow, char *validated)
{
    int i, count=0;
    char aux;

    if(follow==0 && (strcmp(validated,"[]"))!=0) return 0;

    for (i=1; *(validated+i)!=']'; i++)
    {
        aux = *(validated+i);
        if(aux==',') count++;
        else if ((aux<'0' || aux>'9') && aux!=' ') return 0;
    }

    if(i!=1 && count+1!=follow) return 0;

    return 1;
}

int build_user(char* line,FILE* out)
{
    char lineok[strlen(line)+1];
    strcpy(lineok,line);

    char* validated = NULL;
    char* buff2 = line;
    int followers=0, following=0, n;

    for (n=1;n<=10;n++)
    {
        validated = strdup(strsep(&buff2, ";\r\n"));

        // verfication of id, pub_gits and pub_repos
        if ((n==1 || n==9 || n==10)&&check(validated)==0) 
            return 0;
        
        // verification of type
        else if (n==3 && strcmp(validated, "Bot") && strcmp(validated, "User") && strcmp(validated, "Organization")) 
            return 0;

        // verification of created_at
        else if (n==4 && check_Time(validated)==0) 
            return 0;

        //verification of followers
        else if (n==5) 
        {
            if(check(validated)==0) return 0;
            followers = atoi(validated);
        }

        // verification of followers_list
        else if (n==6 && check_list(followers,validated)==0) 
            return 0;

        // verification of following
        else if (n==7) 
        {
            if(check(validated)==0) return 0;
            following = atoi(validated);
        }

        // verification of following_list
        else if (n==8 && check_list(following,validated)==0)
            return 0;
    }

    fprintf(out,"%s",line);

    return 1;
}

int build_commits(char* line,FILE* out)
{
    char lineok[strlen(line)+1];
    strcpy(lineok,line);
    
    char* validated=NULL;
    char* buff2 = line;
    int n;

    for(n=1;n<=5;n++)
    {
        // message 
        validated = strdup(strsep(&buff2, ";\r\n"));
        
        // verfication of repo_id, author_id and committer_id
        if((n==1 || n==2 || n==3)&&check(validated)==0)
            return 0;

        // verfication of commited_at
        else if (n==4 && check_Time(validated)==0) return 0;
    }

    fprintf(out,"%s",line);
    
    return 1;
} 

int build_repos(char* line,FILE* out)
{
    char lineok[strlen(line)+1];
    strcpy(lineok,line);
    
    char* validated = NULL;
    char* buff2 = line;
    int n;

    for(n=1;n<=14;n++)
    {
        // full_name, license, description, language, default_branch
        validated = strdup(strsep(&buff2, ";\r\n"));

        // verfication of id, owner_id, forks_count, open_issues, stargazer_count, size
        if((n==1 || n==2 || n>=11) && check(validated)==0) return 0;

        // verfication of has_wiki
        if(n==5 && strcmp(validated,"True")!=0 && strcmp(validated,"False")!=0)
            return 0;

        // verfication of created_at and updated_at
        if ((n==9 || n==10) && check_Time(validated)==0) 
            return 0;

    }

    fprintf(out,"%s",line);
    
    return 1;
}

int build_main(FILE* toread, FILE* towrite, FILE* errors, int n)
{
    int max_len = 200000, faulty = 0;
    char buff[max_len];
    char lineok[max_len+1];

    fgets(buff, max_len, toread);

    fprintf(towrite,"%s",buff);

    while(fgets(buff, max_len, toread))
    {
        
        strcpy(lineok,buff);
        
        if(find_errors(buff,n)==0)
        {
            if(n==1 && build_user(buff, towrite)==0) faulty++;
            else if(n==2 && build_commits(buff, towrite)==0) {fprintf(errors,"%s",lineok);faulty++;}
            else if(n==3 && build_repos(buff, towrite)==0) faulty++;
        }
        else {fprintf(errors,"%s",lineok);faulty++;}
    }

    return faulty;
}


int main(int argc, char const *argv[])
{
    int faulty = 0;

    printf("starting...\n");
    FILE *fusers = fopen("entrada/users.csv", "r");
    FILE *fusersok = fopen("saida/users-ok.csv","w");
    
    FILE *fcommits = fopen("entrada/commits.csv", "r");
    FILE *fcommitsok = fopen("saida/commits-ok.csv","w");
    
    FILE *frepos = fopen("entrada/repos.csv", "r");
    FILE *freposok = fopen("saida/repos-ok.csv","w");

    FILE *errors = fopen("saida/errors.csv","w");

    if (fusers == NULL || fusersok == NULL ||  fcommits == NULL || fcommitsok == NULL || frepos == NULL || freposok == NULL)
        return -1;
    else printf("All file opened successfully\n");
    
    faulty = build_main(fusers,fusersok, errors,1);
    printf("Users Faulty=%d\n",faulty);

    faulty = build_main(fcommits,fcommitsok,errors,2);
    printf("Commits Faulty=%d\n",faulty);

    faulty = build_main(frepos,freposok,errors,3);
    printf("Repos Faulty=%d\n",faulty);
    
    printf("Everything tested, mission completed.\n");
    
    fclose(fusers);
    fclose(fusersok);
    fclose(frepos);
    fclose(fcommits);
    fclose(freposok);
    fclose(fcommitsok);

    return 0;
}
