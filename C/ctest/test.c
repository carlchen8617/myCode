#include<stdio.h>
#include "test.h"

// This is a comment, that is great
int main(int args, char **argv){
	char o[] ="ok";
	int siz = sizeof(o);
	int loc = *(int*)&o;
	int c[5][6];
	c[2][4]=5;
	printf("The size of %s is %d, the memory loc is %d, and %d \n",o, siz, loc, c[2][4]);
        
        printf("calling external function...\n");
        dosth("do you see me?");
	doanothersth("this is the fucken second call");
	//countit("republic republic republic A regular expression is a sequence of characters used to match a pattern to a string. The expression can be used for searching text and validating input. Remember, a regular expression is not the property of a particular language. POSIX is a well-known library used for regular expressions in C.");
	//countit("I met a traveller from an antique land Who said: ‘Two vast and trunkless legs of stone Stand in the desert. Near them, on the sand, Half sunk, a shattered visage lies, whose frown, And wrinkled lip, and sneer of cold command,Tell that its sculptor well those passions read Which yet survive, stamped on these lifeless things, The hand that mocked them and the heart that fed: And on the pedestal these words appear: “My name is Ozymandias, king of kings: Look on my works, ye Mighty, and despair!” Nothing beside remains. Round the decay Of that colossal wreck, boundless and bare The lone and level sands stretch far away.’");
	countit("‘Two");
	return 0;
}

