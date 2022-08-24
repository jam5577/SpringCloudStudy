package main

import "fmt"

func main() {
	pointer()
	fmt.Println("测试指针")
	a := 1
	b := 2
	res := [3]int{1, 2, 3}
	swap(&a, &b)
	fmt.Println(a, b)
	fmt.Println(res, res[2:3])
}

func reverse(a, b int) {
	a, b = b, a
}

func pointer() {
	// 准备一个字符串类型
	var house = "Malibu Point 10880, 90265"
	// 对字符串取地址, ptr类型为*string
	ptr := &house
	// 打印ptr的类型
	fmt.Printf("ptr type: %T\n", ptr)
	// 打印ptr的指针地址
	fmt.Printf("address: %p\n", ptr)
	// 对指针进行取值操作
	value := *ptr
	// 取值后的类型
	fmt.Printf("value type: %T\n", value)
	// 指针取值后就是指向变量的值
	fmt.Printf("value: %s\n", value)
	// 如果取指针地址呢？
	fmt.Printf("value type: %T\n", &value)
	fmt.Printf("value:%p\n", &value)
}

//交换函数
func swap(a, b *int) {
	*a, *b = *b, *a
}

//判断数组是否相等
