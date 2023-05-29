def test1(a,b):
    c = 1
    while(a < b):
        c = c * 10
        b = b - a
    return c
def test2(a,b,c,d):
    x = test1(5,2)
    a = b % c
    d = a * b
    t = d + x
    return t
a = 2
b = a * 2
if(b <= 8):
    c = test1(a,10)
    print(c)
    print()
    while(a > 0):
        d = test2(c,a,4,2)
        d = d - b
        a = a - 1
        print(d)
