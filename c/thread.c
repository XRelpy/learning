#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

void thread(void)
{
	printf("Thread is start.......\n");
	while(1);
}

int main(int argc, char *argv[])
{
	pthread_t id;
	pthread_create(&id, NULL,(void *)thread ,NULL);
	int j;
	for(j=0; j<3; j++){
		printf("This is the main thread\n");
		sleep(1);
	}
	pthread_join(id, NULL);
	return 0;
}
