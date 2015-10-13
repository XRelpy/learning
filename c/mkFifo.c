#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "markFifo.h"

int main(int argc, char *argv[])
{
	int mk;

	mk = mkfifo(FIFONAME, FIFOMODE);
	if(mk == 0){
		printf("mkfifo sucess--%s\n", FIFONAME);
	}else  {
		fprintf(stderr, "mkfifo Error\n");	
	}
	return 0;		
}
