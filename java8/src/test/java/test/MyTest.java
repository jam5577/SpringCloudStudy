package test;

import anno.MyAnnotation;
import com.jam.anno.OptDate;
import dao.MyInterface;
import entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: SpringbootStudy
 * @description: 测试类
 * @author: Mr.Pu
 * @create: 2022-01-23 23:55
 **/

public class MyTest {

    @Test
    public void testLambda(){
        /**
         * 匿名内部类可以直接new一个接口，并在new出来的接口函数后加上{}表示重写这个接口里的方法
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                o1=1;
                o2=0;
                return Integer.compare(o1,o2);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
        //调用
        TreeSet<Integer> set = new TreeSet<>(comparator);
        System.out.println(set);
    }

    @Test
    public void testLambda1(){
        /**
         * Lambda 表达式，可以创建匿名函数进行调用
         * - 操作符：->
         * - 左侧：参数列表
         * - 右侧：执行代码块 / Lambda 体
         * 口诀：
         * 写死小括号，拷贝右箭头，落地大括号
         * 左右遇一括号省
         * 左侧推断类型省
         * 语法格式：
         * 无参数，无返回值：() -> sout
         */
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);

        TreeSet<Integer> set = new TreeSet<>(comparator);

        /**
         * 例如，对于同一个runnable函数，可以使用匿名内部类和lambda表达式实现
         */
        Runnable runnable = new Runnable() {
            //匿名内部类
            @Override
            public void run() {
                //在局部类中引用同级局部变量
                //只读
                System.out.println("这是匿名内部类");
            }
        };
        runnable.run();

        //语法糖lambda
        Runnable runnable1 = () -> System.out.println("这是lambda表达式");
        runnable1.run();
    }

    public Integer operation(Integer a, Integer b, MyInterface myFun){
        return myFun.count(a, b);
    }

    @Test
    public void testFunctionInterface(){
        MyInterface myFun1 = (a, b) -> a + b;
        MyInterface myFun2 = (a, b) -> a - b;
        MyInterface myFun3 = (a, b) -> a * b;
        MyInterface myFun4 = (a, b) -> a / b;

        System.out.println(myFun1.count(1,2));
        System.out.println(myFun2.count(1,2));
        System.out.println(myFun3.count(1,2));
        System.out.println(myFun4.count(1,2));
        Integer result = operation(1, 2, (x, y) -> x + y);
        System.out.println(result);
    }

    @Test
    public void testReference(){
        /**
         * 若 Lambda 表达式体中的内容已有方法实现，则我们可以使用“方法引用”
         * 对象 :: 实例方法
         * 类 :: 静态方法
         * 类 :: 实例方法   Lambda 参数列表中的第一个参数是方法的调用者，第二个参数是方法的参数时，才能使用 ClassName :: Method
         */
        PrintStream ps = System.out;
        Consumer<String> con1 = (s) -> ps.println(s);
        con1.accept("aaa");
        //上下两个等价
        Consumer<String> con2 = ps::println;
        con2.accept("bbb");

        /**
         * 构造器引用
         * ClassName :: new
         * 需要调用的构造器的参数列表要与函数时接口中抽象方法的参数列表保持一致
         * 数组引用
         * 语法：
         *
         * Type :: new;
         */
        Supplier<List> sup1 = () -> new ArrayList();

        Supplier<List> sup2 = ArrayList::new;
    }

    //以下皆为流相关操作
    @Test
    public void testStream(){
        /**
         * 流 <p>stream</p> 是一个数据渠道，用于操作数据源（集合、数组等）所生成的元素序列
         * 也就是传入数据并在流中进行一系列操作最后返回处理后的值
         * +--------------------+       +------+   +------+   +---+   +-------+
         * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
         * +--------------------+       +------+   +------+   +---+   +-------+
         * 以上流程转换为代码就是：
         * List<Integer> transactionsIds =
         * widgets.stream()
         *              .filter(b -> b.getColor() == RED)
         *              .sorted((x,y) -> x.getWeight() - y.getWeight())
         *              .mapToInt(Widget::getWeight)
         *              .sum();
         * **注意**：
         * 1、stream不会自己存储元素
         * 2、stream不会改变源对象。相反他们会返回一个持有结果的新stream
         * 3、stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行。
         *
         * stream的操作步骤
         * 1、创建stream，一个数据源如集合，数组等，获取一个流
         * 2、中间操作，一个中间操作链，对于数据源进行处理
         * 3、终止操作，一个终止操作，执行中间操作链，并产生结果
         */

        /**
         * 集合流
         *  - Collection.stream() 穿行流
         *  - Collection.parallelStream() 并行流
         */
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //数组流
        //Arrays.stream(array)
        String[] strings = new String[10];
        Stream<String> stream2 = Arrays.stream(strings);

        //Stream 静态方法
        //Stream.of(...)
        Stream<Integer> stream3 = Stream.of(1, 2, 3);

        //无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (i) -> ++i+i++);
        stream4.forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
        /**
         * 筛选、切片
         * 中间操作：
         *
         * filter：接收 Lambda ，从流中排除某些元素
         * limit：截断流，使其元素不超过给定数量
         * skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
         * distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素
         */
        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(103, "W5", 35, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );
        emps.stream()
                .filter((x) -> x.getSize() > 35)//筛选尺寸大于35的
                .limit(3) //短路？达到满足不再内部迭代，限制数量为3个
                .distinct()//除去重复元素
                .skip(1)//舍弃前1个元素
                .forEach(System.out::println);
    }

    @Test
    public void testForEach(){
        /**
         * forEach() 方法用于遍历动态数组中每一个元素并执行特定操作。
         * forEach() 方法的语法为：
         * arraylist.forEach(Consumer<E> action)
         */
        // 创建一个数组
        ArrayList<Integer> numbers = new ArrayList<>();

        // 往数组中添加元素
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        System.out.println("ArrayList: " + numbers);

        // 所有元素乘以 10
        System.out.print("更新 ArrayList: ");

        /**
         * 将 lambda 表达式传递给 forEach
         * forEach可以对arrayList中的每一个元素执行forEach中传入的方法
         */
        numbers.forEach((e) -> {
            e = e * 10;
            System.out.print(e + " ");
        });
    }

    public Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    @Test
    public void testMapper(){
        /**
         * map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
         * flatMap：接收一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流重新连接成一个流
         */
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::print);
        System.out.println();
        /**
         * 返回结果为：
         * ABC
         * [a, b, c]
         * abc
         * 可以看出，map在不改变原数组的情况下进行流操作
         * flatmap是将原数组替换为操作后数据
         */
        System.out.println(list);

        List<String> list1 = Arrays.asList("a", "b", "c");
        MyTest test = new MyTest();
        list1.stream()
                .flatMap(test::filterCharacter)
                .forEach(System.out::print);
    }

    @Test
    public void testSort(){
        /**
         * sorted()：自然排序，从小到大
         * sorted(Comparator c)：定制排序
         */
        List<Integer> list = Arrays.asList(9,7,10,4,5);
        list.stream()
                .sorted() //compareTo()
                .forEach(System.out::println);
        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(108, "W5", 85, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );
        emps.stream()
                .sorted((e1, e2) -> { //compare()
                    if (e1.getSize().equals(e2.getSize())){
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getSize().compareTo(e2.getSize());
                    }
                })
                .forEach(System.out::println);
    }

    public enum Status {
        FREE, BUSY, VOCATION;
    }
    @Test
    public void testMatch(){
        /**
         * 终止操作：
         *
         * allMatch：检查是否匹配所有元素
         * anyMatch：检查是否至少匹配一个元素
         * noneMatch：检查是否没有匹配所有元素
         * findFirst：返回第一个元素
         * findAny：返回当前流中的任意元素
         * count：返回流中元素的总个数
         * max：返回流中最大值
         * min：返回流中最小值
         */
        List<Status> list = Arrays.asList(Status.FREE, Status.BUSY, Status.VOCATION);

        boolean flag1 = list.stream()
                .allMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag1);

        boolean flag2 = list.stream()
                .anyMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag2);

        boolean flag3 = list.stream()
                .noneMatch((s) -> s.equals(Status.BUSY));
        System.out.println(flag3);

        // 避免空指针异常
        /**
         * 一个容器对象，可能包含也可能不包含非空值。
         * 如果存在值，{@code isPresent()} 将返回 {@code true} 并且 {@code get()} 将返回该值。
         * 提供了取决于包含值是否存在的其他方法，例如 {@link orElse(java.lang.Object) orElse()}（如果值不存在则返回默认值）
         * 和 {@link ifPresent(java.util.function.Consumer) ifPresent()} （如果值存在则执行代码块）。
         */
        Optional<Status> op1 = list.stream()
                .findFirst();
        // 如果Optional为空 找一个替代的对象
        Status s1 = op1.orElse(Status.BUSY);
        System.out.println(s1);

        Optional<Status> op2 = list.stream()
                .findAny();
        System.out.println(op2);

        System.out.println(list.stream().findAny());
        long count = list.stream()
                .count();
        System.out.println(count);
    }

    @Test
    public void testCollect(){
        /**
         * 归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中的数据反复结合起来，得到一个值
         * 收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer integer = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(integer);

        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(103, "W5", 35, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );

        //放入List,list有序，可更改
        List<String> list1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list1.forEach(System.out::println);

        //放入Set，set无序，不可更改
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
//                .forEach(System.out::println);
        set.forEach(System.out::println);

        //放入LinkedHashSet
        LinkedHashSet<String> linkedHashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        linkedHashSet.forEach(System.out::println);

        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getPrice));
        System.out.println(avg);

        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getPrice));
        System.out.println(sum);

        //最大值
        Optional<Employee> max = emps.stream()
//                .max(Comparator.comparingDouble(Employee::getPrice));
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getPrice(), e2.getPrice())));
        System.out.println(max.get());
//        System.out.println(max.isPresent());

        //最小值
        Optional<Double> min = emps.stream()
                .map(Employee::getPrice)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());

        //分组
        Map<Integer, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getCode));
        System.out.println(map);

        //多级分组
        Map<Integer, Map<String, List<Employee>>> mapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getCode, Collectors.groupingBy((e) -> {
                    if (e.getSize() > 35) {
                        return "开除";
                    } else {
                        return "继续加班";
                    }
                })));
        System.out.println(mapMap);

        //分区
        Map<Boolean, List<Employee>> listMap = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getPrice() > 4321));
        System.out.println(listMap);

        //总结
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getPrice));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
        System.out.println(dss.getCount());
        System.out.println(dss.getAverage());

        System.out.println(Collectors.summarizingInt(Employee::getCode));
        //连接
        String str = emps.stream()
                .map(Employee::getName)
//                .collect(Collectors.joining());
                .collect(Collectors.joining("-")); //可传入分隔符
        System.out.println(str);
    }

    @Test
    public void testCase(){
        /**
         * *案例一：**给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
         * (如：给定【1，2，3，4，5】，返回【1，4，9，16，25】)
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream()
                .map((x) -> x * x)
                .forEach(System.out::println);
        /**
         * *案例二：**怎样使用 map 和 reduce 数一数流中有多少个 Employee 呢？
         */
        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(103, "W5", 35, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );
        Optional<Integer> reduce = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void testOptional(){
        /**
         * **定义：**Optional 类 (java.util.Optional) 是一个容器类，代表一个值存在或不存在，
         * 原来用 null 表示一个值不存在，现在用 Optional 可以更好的表达这个概念；并且可以避免空指针异常
         * 常用方法：
         *
         * Optional.of(T t)：创建一个 Optional 实例
         * Optional.empty(T t)：创建一个空的 Optional 实例
         * Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例，否则空实例
         * isPresent()：判断是否包含某值
         * orElse(T t)：如果调用对象包含值，返回该值，否则返回 t
         * orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回 s 获取的值
         * map(Function f)：如果有值对其处理，并返回处理后的 Optional，否则返回 Optional.empty()
         * flatmap(Function mapper)：与 map 相似，要求返回值必须是 Optional
         */

        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        Optional<Employee> op1 = Optional.empty();
        Employee employee1 = op1.get();
        Optional<Employee> op2 = Optional.ofNullable(new Employee());
        Employee employee2 = op2.get();
        Optional<Employee> op3 = Optional.ofNullable(new Employee());
        if (op.isPresent()) {
            Employee employee3 = op3.get();
        }
    }

    @Test
    public void testDate(){
        /**
         * Instant：以 Unix 元年 1970-01-01 00:00:00 到某个时间之间的毫秒值
         */
        // 默认获取 UTC 时区 (UTC：世界协调时间)
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        //带偏移量的时间日期 (如：UTC + 8)
        OffsetDateTime odt1 = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt1);

        //转换成对应的毫秒值
        long milli1 = ins1.toEpochMilli();
        System.out.println(milli1);

        //构建时间戳
        Instant ins2 = Instant.ofEpochSecond(60);
        System.out.println(ins2);
        /**
         * 时间 / 日期 差
         * Duration：计算两个时间之间的间隔
         * Period：计算两个日期之间的间隔
         */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Duration dura1 = Duration.between(ins1, ins2);
        System.out.println(dura1.getSeconds());
        System.out.println(dura1.toMillis());

        LocalDate ld1 = LocalDate.of(2016, 9, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);  // ISO 标准
        System.out.println(period.getYears());
        System.out.println(period.toTotalMonths());
    }

    @Test
    public void testTemporalAdjuster(){
        /**
         * TemporalAdjuster:时间矫正器，有时我们可能需要获取例如将日期调整到“下周日”等操作 是接口
         * TemporalAdjusters:该类通过实现静态方法提供了大量的常用TemporalAdjuster的实现 是实现类
         */
        LocalDate nextSunday = LocalDate.now().with(
                TemporalAdjusters.next(DayOfWeek.SUNDAY)
        );
        System.out.println(nextSunday);
        //TemporalAdjusters：时间校正器
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        //指定日期时间中的 年 月 日 ...
        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println(ldt2);

        //指定时间校正器
        LocalDateTime ldt3 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义时间校正器
        LocalDateTime ldt5 = ldt1.with((ta) -> {
            LocalDateTime ldt4 = (LocalDateTime) ta;
            DayOfWeek dow1 = ldt4.getDayOfWeek();
            if (dow1.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow1.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    @Test
    public void testDateFormatter(){
        //默认格式化
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime ldt1 = LocalDateTime.now();
        String str1 = ldt1.format(dtf1);
        System.out.println(str1);

        //自定义格式化 ofPattern
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt2 = LocalDateTime.now();
        String str2 = ldt2.format(dtf2);
        System.out.println(str2);

        //解析
        LocalDateTime newDate = ldt1.parse(str1, dtf1);
        System.out.println(newDate);
    }

    //重复注解
    @Test
    @MyAnnotation("Hello ")
    @MyAnnotation("World")
    @MyAnnotation
    public void testAnnotation() throws NoSuchMethodException {
        Class<MyTest> clazz = MyTest.class;
        Method method = clazz.getMethod("testAnnotation");
        MyAnnotation[] mas = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation ma : mas) {
            System.out.print(ma.value());
        }
    }
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    @Test
    @OptDate
    public void testAnno(){

    }
}
