a = 3
def test(b,c):
    b = 6
    return b
def test2(b):
    return b
a = test(5,4)
b = test2(100)
print(a)
print()
print()
print(b)
