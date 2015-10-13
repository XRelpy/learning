#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>


int main(int argc, char *argv[])
{
	int pipefd[2];
	pid_t cpid;
	char buf;
	if(argc != 2){
		fprintf(stderr, "Usage:%s <string>\n", argv[0]);
	}
	if(pipe(pipefd) == -1){
		perror("pipe");
	}	
	cpid = fork();
	if(cpid == -1){
		perror("Fork");
	}
	if(cpid == 0){
		close(pipefd[1]);
		while(read(pipefd[0], &buf,1) > 0)
			write(STDOUT_FILENO, &buf, 1);
		close(pipefd[0]);
	}else{
		close(pipefd[0]);
		write(pipefd[1], argv[1], strlen(argv[1]));
		sleep(1);
		close(pipefd[1]);
		wait(NULL);
	}
}

