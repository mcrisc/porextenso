#!/usr/bin/env python
# coding=utf-8


def main():
    for line in open('testdata.txt', 'r'):
        tokens = line.split(';')
        print('assertTrue("%s".equals(cw.write(new BigDecimal("%s"))));' %
              (tokens[1].strip(), tokens[0].strip()))


if __name__ == "__main__":
    main()
