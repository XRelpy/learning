#include <stdio.h>

int main(int argc, char* argv[])
{
	static int a = 3;
	const int b = 4;
	int c = 5;
	c = a + b;
	printf("%d\n", c);
	return 0;
}
