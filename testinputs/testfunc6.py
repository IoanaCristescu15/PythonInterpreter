def gcdrec(a,b):
    if(b == 0):
        ans = a
    if(b != 0):
        c = a % b
        ans = gcdrec(b,c)
    return ans
def gcdloop(a,b):
    while(b != 0):
        c = a
        a = b
        b = c % b
    return a
def gcdcompare(a,b):
    c = gcdrec(a,b)
    print(c)
    d = gcdloop(a,b)
    print(d)
    e = d - c
    if(e != 0):
        print(e)
    return e
c = gcdcompare(20,40)
c = gcdcompare(20,30)
c = gcdcompare(22,30)
c = gcdcompare(24,32)
c = gcdcompare(25,0)
c = gcdcompare(41231231235,230492385)

