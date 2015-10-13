#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include "markFifo.h"

int main(int argc, char *argv[])
{
	int fd;
	char *buf, *data;	
	fd = open(FIFONAME, O_RDWR,FIFOMODE);
	buf = (char *)malloc(64);
	data = (char *)malloc(64);	
	//buf = argv[1];
	//write(fd, buf, strlen(buf));
	read(fd, data, DATASIZE);
	printf("Fifo Data:%s\n", data);
	
	close(fd);
	return 0;
}
