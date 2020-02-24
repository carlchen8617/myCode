#include<stdio.h>
#include <string.h>
#include <stdlib.h>


void countit(char* str){
     int i,j=0,k=0;
     char words[200][20];
     char* wordsa =(char*) calloc(1,sizeof(words));
     memcpy(words,wordsa, sizeof(words));
     struct dict{
	char* key;
        int value;
     };

    // memset(words, 0, sizeof(words[0][0]) *200 * 20);

     printf("%d\n",strlen(str));
     printf("%s\n", str); 
     
     for(i=0; i< strlen(str); i++){
	    if((char)str[i] != ' '){

                printf("Not white space %x\n", str[i]);
	        if((char)str[i] != ',' && (char)str[i] !='!' &&  str[i]!=0xffffffe2  &&  str[i]!=0xffffff80 &&  str[i]!=0xffffff98 && (char)str[i] !=(char)(0x92)){ 
		 words[j][k]=(char)str[i];
		//   *(words + i*20 + k )= (char)str[i];
                 k++;
		}
	    }
	    else if ((char)str[i] == ' '){
		   /// printf("%d A  white space %c\n",j, str[i]);
		    k=0;
		    j++;
	    }

     }
          
     struct dict Dict[j+1];
     struct dict* Dicta = malloc(sizeof(Dict[0])*(j+1));
     memcpy(Dicta,Dict, sizeof(Dict[0])*(j+1));
     printf("before%d\n",j);
     for(i=0;i<j+1;i++){
	     printf("before  %x\n",Dict[i].key);
	     printf("before  %x\n",Dict[i].value);
     }
     printf("after%d\n", j);
     memset(Dict, 0, sizeof(Dict[0])*(j+1));
     for(i=0;i<j+1;i++){
	     printf("after   %x\n",Dict[i].key);
	     printf("after   %x\n",Dict[i].value);
     }
     int rr=0,tt=0;
     printf("size is %d\n", j+1);


	for(i=0; i<j+1; i++){
	      for(k=0; k<j+1; k++){
		      if(Dict[k].key ){
		      if( strcmp((char*)Dict[k].key,(char*)words[i])==0){
			     
			      Dict[k].value++;
			      tt=1;
			      break;
		        }
		      }

	      }
	      if(tt==1){
		      tt=0;
		      continue;
	      }
	      else{
		      Dict[rr].key=words[i];
		      Dict[rr].value=1;
		      rr++;
	      }
	}

	        
	printf("\n\n*********** Here is the repeat counter result**********\n\n");  

	for(k=0; k < j+1; k++){
	       if(Dict[k].key){

	           printf("%s: %d\n", Dict[k].key, Dict[k].value);
	       }


	      }



	printf("\n\n******************************************************\n\n");  
	printf("free memory for words array...\n");
        free(wordsa);

	printf("free memory for struct array...\n");
	free(Dicta);	

}
