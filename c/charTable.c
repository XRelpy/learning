#include <stdio.h>

static const char *table(unsigned char code)
{
	static const char *type[] = {
		"BIOS",
		"System"
	};
	
	return type[code];
}

int main(int argc, char *argv[])
{
	printf("%s\n", table(1));
}
