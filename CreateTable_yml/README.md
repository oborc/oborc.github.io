##### Spring-boot 通过配置文件和schema.sql 文件进行表的建立

最坑的是Spring-datasource-initialization-model这个属性。找了很多博客都没有说这个属性需要配置，刚开始怎么都不能建表成功。最后找到一个博客说明了.在spring 2.0以上的版本中。如果要通过schema建立表的话需要配置这个属性。默认为always.



