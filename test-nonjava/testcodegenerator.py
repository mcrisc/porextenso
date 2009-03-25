#!/usr/bin/env python
# coding=utf-8


# This file is part of PorExtenso.
# 
#     PorExtenso is free software: you can redistribute it and/or modify
#     it under the terms of the GNU Lesser General Public License as published by
#     the Free Software Foundation, either version 3 of the License, or
#     (at your option) any later version.
# 
#     PorExtenso is distributed in the hope that it will be useful,
#     but WITHOUT ANY WARRANTY; without even the implied warranty of
#     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#     GNU Lesser General Public License for more details.
# 
#     You should have received a copy of the GNU Lesser General Public License
#     along with PorExtenso.  If not, see <http://www.gnu.org/licenses/>.
#     
#     
# Copyright 2008, Marcelo Criscuolo.    


def main():
	for line in open('testdata.txt', 'r'):
		tokens = line.split(';')
		print 'assertTrue("%s".equals(cw.write(new BigDecimal("%s"))));' % (tokens[1].strip(), tokens[0].strip())


if __name__ == "__main__":
	main()
