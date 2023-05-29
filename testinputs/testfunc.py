#CommentTest
def gcd(a,b):
    if(b == 0):
        retval = a
    if(b != 0):
        c = a % b
        retval = gcd(b,c)
    return retval
a = gcd(808,120)
print(a)
