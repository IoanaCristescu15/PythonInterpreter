a = 0
tot = 0
while(a < 100000):
    b = a % 9
    if(b == 0):
        tot = tot + a
    a = a + 1
print(tot)

