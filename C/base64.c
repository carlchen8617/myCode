/#include <stdio.h>
#include <assert.h>
#include "base64.h"

static char *base64 = 
 "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

void base64_encode( const unsigned char *input, int len, unsigned char *output )
{  
  do
  {
    *output++ = base64[ ( input[ 0 ] & 0xFC ) >> 2 ]; //mask away last 2 bit and take first 6 bits and put in output
  
    if ( len == 1 )
    {
      *output++ = base64[ ( ( input[ 0 ] & 0x03 ) << 4 ) ]; //take last 2 bits and move them 4 bits up to make xx0000
      *output++ = '=';
      *output++ = '=';
      break;
    }

    *output++ = base64[
       ( ( input[ 0 ] & 0x03 ) << 4 ) | ( ( input[ 1 ] & 0xF0 ) >> 4 ) ]; //take last 2 bits and move 4 bits up to take high order. Input[1]'s lower 4 bits are masked away and high 4 bits are shifted to lower position to be ORed with input[0}'s 2 bits to make up a Base64 "byte".

    if ( len == 2 )
    {
      *output++ = base64[ ( ( input[ 1 ] & 0x0F ) << 2 ) ]; // take last 4 bits and move them up 2 places to make xxxx00
      *output++ = '=';
      break;
    }

    *output++ = base64[
       ( ( input[ 1 ] & 0x0F ) << 2 ) | ( ( input[ 2 ] & 0xC0 ) >> 6 ) ]; // take input[1]'s lower 4 bits and move up 2 places. input[2]'s high order 2 bits are shifted down 6 places to be ORed into a base64 "byte"
    *output++ = base64[ ( input[ 2 ] & 0x3F ) ]; // input[2]'s last 6 bits are taken to form base64 "byte"
    input += 3;
  }
  while ( len -= 3 );

  *output = '\0';
}

static int unbase64[] =
{
  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52,
  53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1,
  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
  16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1,
  26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
  42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1
}; //ascii table! The input come in as regular bytes. for example, if a byte is 43(decimal),input[43] would be 62(base64), if it is 48, input[48] would be base64 0, etc..

int base64_decode( const unsigned char *input, int len, unsigned char *output )
{
  int out_len = 0, i; 
  assert( !( len & 0x03 ) ); // Is an even multiple of 4

  do
  {
    for ( i = 0; i <= 3; i++ )
    {
      // Check for illegal base64 characters
      if ( input[ i ] > 128 || unbase64[ input[ i ] ] == -1 )
      {
        fprintf( stderr, "invalid character for base64 encoding: %c\n", 
             input[ i ] );
        return -1;
      }
    }
    *output++ = unbase64[ input[ 0 ] ] << 2 |
           ( unbase64[ input[ 1 ] ] & 0x30 ) >> 4; //find char in unbase array and move left 2 places, then find next char in unbase array and mask away last 4 bits and move right 4 places(2 begining bits left). finally bitwise OR to make a ASCII byte. The use of masks also bring up base64 to 8 bits(not 6)
    out_len++;
    if ( input[ 2 ] != '=' ) // not at end
    {
      *output++ = ( unbase64[ input[ 1 ] ] & 0x0F ) << 4 |
            ( unbase64[ input[ 2 ] ] & 0x3C ) >> 2; // find input[1] char in unbase array and mask away first 4 bits( because the first bits is always empty base64, and the next 2 bits was used in above run, then the last 4 bits are moved to take the high order.  Then input[2]'s last 2 bits is masked away and the first bits are shifted to lower order. then they are OR-ed to make a ASCII byte.  
      out_len++;
    }
  
    if ( input[ 3 ] != '=' )
    {
      *output++ = ( unbase64[ input[ 2 ] ] & 0x03 ) << 6 |
            unbase64[ input[ 3 ] ]; // input[2]'s last 2 bits are shifted to high order and Or-ed with input[3] as lower 6 bits to make up ASCII byte.
      out_len++;
    } 
    input += 4;
  }
  while ( len -= 4 ); 
  return out_len;
} 
