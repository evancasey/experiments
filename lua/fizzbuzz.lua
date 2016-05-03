local function fizzbuzz(range) 
  for i = 0, range do 
    if i % 3 == 0 and i %5 == 0 then
      print("FIZZBUZZ")
    elseif i % 3 == 0 then
      print("FIZZ")
    elseif i % 5 == 0 then
      print("BUZZ")
    else
      print(i)
    end 
  end 
end

fizzbuzz(100)
