# Introduction to Scala and Spark

Repository documenting an introduction to Scala and Spark.

---

# Notebooks
0. [Basics](notebooks/00\ -\ basics.scala.ipynb)
1. [Collections](notebooks/01\ -\ collections.scala.ipynb)
2. [Flow Controls](notebooks/02\ -\ flowcontrols.scala.ipynb)

---

# Installation
* [Mac OS](https://medium.freecodecamp.org/installing-scala-and-apache-spark-on-mac-os-837ae57d283f)
* [Installing Scala kernel for Jupyter](http://almond-sh.github.io/almond/stable/docs/quick-start-install)

# Why Scala?

* General purpose programming language to address issues with Java
* Compiled to Java bytecode to run on a JVM (Java libraries can be used)
* Functional programming features

# Why Spark?

## Distributed Storage Systems

* Big = bigger than your computer RAM
* Solutions: Put your data on a SQL database or a distributed storage system (=master computer controlling multiple computers through a network)
* Key advantages of distributed system is fault tolerance (a few cores out is OK)
* Typical architecture is Hadoop = a way to distribute files across many machines
* HDFS = Hadoop Distributed File System
  - Each memory block is replicated 3x and distributed to support fault tolerance
* MapReduce to allow computations on the distributed data
  - Splits up the computations on the distributed data
  - Job Tracker (master) and Task Trackers (on worker nodes)

## So why Spark?
* Open source project on Apache released in 2013
* Improves on the concepts of using distribution
* Flexible alternative to MapReduce that works on many storage systems, NOT ONLY HDFS!
* 100x faster than MapReduce because MapReduce writes data to disk after each map/reduce operation, while Spark keeps data in memory as far as possible and can spill to disk if needed
* RDD = Resilient Distributed Dataset (a distributed collection of data)
  - immutable
  - lazy evaluations so distinguish between Transformations ("instructions") and Actions (actually carries out transformations)
* Spark DataFrames are still RDDs under the hood
 
