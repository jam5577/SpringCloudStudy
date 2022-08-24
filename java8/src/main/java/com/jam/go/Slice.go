package main

import (
	"container/list"
	"fmt"
	"time"
)

func main() {
	var a []int
	a = append(a, 1)                 // 追加1个元素
	a = append(a, 1, 2, 3)           // 追加多个元素, 手写解包方式
	a = append(a, []int{1, 2, 4}...) // 追加一个切片, 切片需要解包
	fmt.Println(a)
	rangeTest()
	mapTest()
	thread()
}

func rangeTest() {
	a := []int{1, 2, 3, 6, 5, 4, 8}
	for i, v := range a {
		fmt.Println(i, v)
	}
	var l list.List
	l.PushFront("any")
	element := l.PushBack("end")
	l.InsertBefore("first", element)
}

func mapTest() {
	var mapLit map[string]int
	//var mapCreated map[string]float32
	var mapAssigned map[string]int
	mapLit = map[string]int{"one": 1, "two": 2}
	mapCreated := make(map[string]float32)
	mapAssigned = mapLit
	mapCreated["key1"] = 4.5
	mapCreated["key2"] = 3.14159
	mapAssigned["two"] = 3
	fmt.Printf("Map literal at \"one\" is: %d\n", mapLit["one"])
	fmt.Printf("Map created at \"key2\" is: %f\n", mapCreated["key2"])
	fmt.Printf("Map assigned at \"two\" is: %d\n", mapLit["two"])
	fmt.Printf("Map literal at \"ten\" is: %d\n", mapLit["ten"])
}

func thread() {
	c := make(chan int)
	go func() {
		time.Sleep(1 * time.Second)
		c <- 1
		c <- 2
		c <- 3
		fmt.Printf("%s", "测试thread")
		close(c)
	}()
	for v := range c {
		fmt.Println(v)
	}
}
