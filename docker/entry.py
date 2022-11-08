
def entry(a, b,df):
    if len(a) == 0:
        print(df)
        exit()
    elif len(b) == 0:
        print(df)
        exit()
    else:
       c = sorted(a,reverse=True)
       print(c)
       d = sorted(b,reverse=True)
       print(d)
       if c[0] > d[0]:
           df+=1
           c.remove(c[0])
           d.remove(d[0])
           entry(c,d,df)
       elif c[0] == d[0]:
           c.remove(c[0])
           d.remove(d[0])
           entry(c,d,df)
       elif c[0] < d[0]:
           d.remove(d[0])
           entry(c,d,df)
             
       print(df)

if __name__ == "__main__":
    df=0
    a = [100,8, 23,1, 9]
    b = [4,33, 76, 25, 24]
    entry(a,b,df)
