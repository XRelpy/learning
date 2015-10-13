#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *getCmd(void)
{
	char *cmd;
	cmd = (char *)malloc(24);
	printf("Fs:");
	scanf("%s",cmd);
	return *cmd;
}

int main(int argc, char *argv[])
{
	char *cmd, *opt;
	unsigned int Flag;
	Flag = 1;
	while(Flag){
		
		
	}
}
