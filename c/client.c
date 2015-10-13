#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/un.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <string.h>

int main()
{
 int sockfd;
 int len;
 struct sockaddr_un address;
 int result;
 int i,byte;
 char ch_recv,ch_send;
 sockfd=socket(AF_UNIX,SOCK_STREAM,0);
 if(sockfd==-1)
 {
  perror("socket");
  exit(1);
 }
 address.sun_family=AF_UNIX;
 strcpy(address.sun_path,"server_socket");
 len=sizeof(address);
 result=connect(sockfd,(struct sockaddr*)&address,len);
 if(result==-1)
 {
  printf("ensure the server is up\n");
  perror("connect");
  exit(1);
 }
 for(i=0,ch_send='A';i<5;i++,ch_send++)
 {
  if(write(sockfd,&ch_send,1)==-1)
  {
   perror("write");
   exit(1);
  }
  if(read(sockfd,&ch_recv,1)==-1)
  {
   perror("read");
   exit(1);
  }
  printf("receive from server is %c\n",ch_recv);
 }

 close(sockfd);
 return 0;
}
