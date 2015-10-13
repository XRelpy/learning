#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <stdlib.h>
#include <unistd.h>

int main(int argc, char * argv[])
{
	int fd;
	fd = open("/proc/meminfo", O_RDONLY);
	
	char *buf;
	read(fd, buf, 1024);
	close(fd);
	printf("%s", buf);
}
