#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define CMDMAP(cmd, argc) K_##cmd,
enum cmdList{
	CMDMAP(led, 1010)
	CMDMAP(led, 1011)
};

struct cmd{
	const char *name;
	int argc; 
};

int main(int argc, char *argv[])
{
	struct cmd *aCmd;
	aCmd = (struct cmd *)malloc(sizeof(struct cmd));	

}
