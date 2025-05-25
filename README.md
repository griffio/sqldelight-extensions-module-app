# SqlDelight 2.1.x extensions module support prototype 

https://github.com/cashapp/sqldelight

**Experimental**

Use with SqlDelight `2.1.0`

Maven Central https://central.sonatype.com/artifact/io.github.griffio/sqldelight-extensions/versions

e.g.

```
sqldelight {
    databases {
        create("Sample") {
            deriveSchemaFromMigrations.set(true)
            migrationOutputDirectory = migrationsDir
            migrationOutputFileFormat = ".sql"
            packageName.set("griffio.queries")
            dialect(libs.sqldelight.postgresql.dialect)
            module(project("io.github.griffio:sqldelight-extensions:0.0.1"))
        }
    }
}
```

---

Support for some existing modules loaded in a single module. 

This is because SqlDelight can currently only support a single module 
[ExtensionsModule.kt](https://github.com/griffio/sqldelight-extensions-module-app/blob/master/extensions-module/src/main/kotlin/griffio/ExtensionsModule.kt)

The modules have to be manually linked for all the overridden rules and
manually set up the inheritance chain of TypeResolvers.

Module Bundle

* PostGis
* Bm25
* PgVector
* PgCrypto
* Bits and Bytes

More can be added if useful
