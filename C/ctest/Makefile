copyfile: a runit 
	cp a aa
	

a:
	touch a

runit: test.c dosth.c findrepeat.c
	$(CC) $^ -o $@

.PHONY: clean

clean:
	rm a* && rm -f runit
