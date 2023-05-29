def func(ant,bear,cat,dog):
    bear = bear + cat
    cat = ant % dog
    a = bear - cat
    return a
a = 5
c = 54
numtimes = 40
while(numtimes > 0):
    a = func(a,2,c,4)
    c = func(c,c,c,3)
    numtimes = numtimes - 1
print(a)
print(c)
