local function calc_profit(prices)
  max_profit = 0
  buy_index = 0
  sell_index = 0

  for buy_ptr = 1, #prices do
    buy_price = prices[buy_ptr]
    for sell_ptr = buy_ptr, #prices do
      sell_price = prices[sell_ptr]
      profit = sell_price - buy_price
      if profit > max_profit then
        max_profit = profit
        buy_index = buy_ptr
        sell_index = sell_ptr
      end
    end
  end

  return max_profit
end

max_profit = calc_profit({70,4,5,3,8,7,4,8,9,1,2,5,7,11,13,22,4,2,30,3,2,2,0,2,5,23})
print(max_profit)
