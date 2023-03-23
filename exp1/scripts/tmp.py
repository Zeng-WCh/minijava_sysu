nonrecur = '''user    0m0.051s
user    0m0.058s
user    0m0.062s
user    0m0.064s
user    0m0.062s
user    0m0.073s
user    0m0.069s
user    0m0.043s
user    0m0.049s
user    0m0.070s
user    0m0.046s
user    0m0.079s
user    0m0.063s
user    0m0.072s
user    0m0.074s
user    0m0.053s
user    0m0.066s
user    0m0.069s
user    0m0.076s
user    0m0.054s'''

recur = """user    0m0.069s
user    0m0.077s
user    0m0.034s
user    0m0.071s
user    0m0.042s
user    0m0.067s
user    0m0.059s
user    0m0.055s
user    0m0.058s
user    0m0.077s
user    0m0.084s
user    0m0.075s
user    0m0.063s
user    0m0.059s
user    0m0.063s
user    0m0.062s
user    0m0.050s
user    0m0.060s
user    0m0.056s
user    0m0.045s"""


nonrecur = nonrecur.split('\n')
recur = recur.split('\n')

for i in range(len(nonrecur)):
    nonrecur[i] = nonrecur[i].split('m')[-1]

for i in range(len(recur)):
    recur[i] = recur[i].split('m')[-1]

assert len(nonrecur) == len(recur)

print("| 序号 | 递归 | 非递归 |")
print("| :--: | :----------: | :--------------: |")
for i in range(len(nonrecur)):
    print("| {} | {} | {} |".format(i+1, recur[i], nonrecur[i]))
