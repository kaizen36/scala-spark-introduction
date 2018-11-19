/*
    Based on Spark docs and example dataset

    Usage:
    $ spark-shell
    scala> :load dataframes.scala
*/

println("Loading people.json")
// note that this uses the spark session created by spark shell when it runs
// To get or create a Spark session
// import org.apache.spark.sql.SparkSession
// val spark = SparkSession.builder().getOrCreate()

val people = spark.read.json("people.json")

println("This is the dataset schema")
people.printSchema()

println("These are the top 20 rows of the dataset")
people.show()

println("Filter column")
people.select("name").show()

println("Filter columns")
people.select($"name",$"age").show()

println("Filter rows")
people.filter($"age" > 21).show()

println("Count people over 21 by age")
people.filter($"age" > 21).groupBy("age").count().show()

// --- Use Spark SQL ---
// First set df as a SQL View
println("Now using Spark SQL")
people.createOrReplaceTempView("people")
val sql_people = spark.sql("SELECT * FROM people")
sql_people.show()

("Filter rows")
val sql_people_filter = spark.sql("SELECT * FROM people WHERE age > 21")
sql_people_filter.show()


// Create a dataframe
println("Loading CitiGroup2006_2008")
val df_raw = spark.read.csv("CitiGroup2006_2008")

// All columns treated as strings
df_raw.printSchema()

// Load again with more sensible options
println("Now treat first row as header and infer schema")
val df = spark.read.option("header","true").option("inferSchema","true").csv("CitiGroup2006_2008")

df.printSchema()

println("First 5 rows:")
for(row <- df.head(5)){
  println(row)
}

// Describe
df.describe().show()

// The below are two ways of doing the same thing
df.filter($"Close" === 480 || $"High" < 480).show()
df.filter("Close = 480 or High < 480").show()

// Make a new dataframe with a new column "HighMinusLow"
val df2 = df.withColumn("HighMinusLow", df("High")-df("Low"))
df2.describe().show()

// Tranform returned dataframe to an array
val df_low = df2.filter("Close < 480 and High < 480").collect()

/////////////////////////

// More functions
// count returns a long int
val C = df.count()
println(s"Number of rows in df = $C")

// Pearson correlation
println("Correlation between High and Low")
df.select(corr("High","Low")).show()

// more functions at
// http://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.functions$
df.select(stddev("High"), countDistinct("Low")).show()
// or
df.select(stddev(df("High"))).show()

//df.groupby("High").sum()
// Note that select is a lazy evaluation that is only performed at an
// action such as show or collect

// Aggregate functions
val df3 = df.withColumn("Month", date_format(df("Date"), "M"))
df3.groupBy("Month").mean().orderBy("Month desc").show()
