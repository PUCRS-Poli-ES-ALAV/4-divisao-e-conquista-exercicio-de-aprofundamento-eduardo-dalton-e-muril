class Knacksaco:
    def __init__(self, cost, value):
        self.cost = cost
        self.value = value

    def __repr__(self):
        return f"Knacksaco(cost={self.cost}, value={self.value})"

def knapsack(capacity, items):
    n = len(items)
    dp = [[0 for _ in range(capacity + 1)] for _ in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, capacity + 1):
            if items[i - 1].cost > j:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - items[i - 1].cost] + items[i - 1].value)

    result = []
    w = capacity
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i - 1][w]:
            result.append(items[i - 1])
            w -= items[i - 1].cost

    return result

items = [Knacksaco(1, 1), Knacksaco(3, 4), Knacksaco(4, 5), Knacksaco(5, 7)]
capacity = 7
print(knapsack(capacity, items))