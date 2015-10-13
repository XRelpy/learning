#include <stdio.h>

struct testContent{
	int id;
	char name[12];
} test ={
	.id = 0x0,
	.name = "XXXXXXXX",
};

int main(int argc, char *argv[])
{
	printf("id:%d name:%s\n",(test.id), (test.name));

	return 0;
}
