a = 16
b = a % 2
if(b == 0):
    c = -2
if(b != 0):
    c = 2
while(c < 0):
    print(c)
    a = a / 2
    b = a % 2
    if(b == 0):
        c = -2
    if(b != 0):
        c = 2
    
