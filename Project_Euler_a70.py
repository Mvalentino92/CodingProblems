from sympy.ntheory import totient
mini = 1e7
sol = 0
for n in range(2,100000):
    nval = n
    dn = [int(x) for x in str(nval)]
    list.sort(dn)
    t = totient(n)
    tval = t
    dt = [int(x) for x in str(tval)]
    list.sort(dt)
    if dn == dt:
        cmin = float(n)/t
        if cmin < mini:
            mini = cmin
            sol = n




print(sol)
