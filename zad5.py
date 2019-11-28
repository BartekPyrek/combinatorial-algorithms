def alg13(data):
# Instrukcja yield jest używana wyłącznie przy definiowaniu funkcji generującej i występuje wówczas w jej treści.
# Wywołanie funkcji generującej zwraca tak zwany iterator generujący, lub po prostu generator. 
# Wykonywanie treści funkcji generującej następuje każdorazowo po wywołaniu metody next() generatora, 
# aż do czasu, gdy funkcja wygeneruje wyjątek.
    yield [data]
    if len(data)>1:
        for i in range(1, len(data)):
            for rec in alg13(data[i:]):
                yield [data[:i]] + rec

for digit in alg13([1,2,3,4]):
    print(digit)