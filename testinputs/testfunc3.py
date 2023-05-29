def func(a,b):
    a = a + b
    return a
a = 5
b = 7
c = a * b
print(a)
print(b)
print(c)
b = func(a,b)
print(a)
print(b)
print(c)
a = func(b,c)
print(a)
print(b)
print(c)
c = func(c,c)
print(a)
print(b)
print(c)
