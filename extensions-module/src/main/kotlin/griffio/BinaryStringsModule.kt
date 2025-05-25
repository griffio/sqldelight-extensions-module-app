package griffio

import app.cash.sqldelight.dialect.api.IntermediateType
import app.cash.sqldelight.dialect.api.PrimitiveType.BLOB
import app.cash.sqldelight.dialect.api.PrimitiveType.TEXT
import app.cash.sqldelight.dialect.api.SqlDelightModule
import app.cash.sqldelight.dialect.api.TypeResolver
import app.cash.sqldelight.dialects.postgresql.PostgreSqlType
import com.alecstrong.sql.psi.core.psi.SqlFunctionExpr

class BinaryStringsModule : SqlDelightModule {
    override fun typeResolver(parentResolver: TypeResolver): TypeResolver = PgCryptoResolver(parentResolver)

    override fun setup() {
    }
}

open class BinaryStringsResolver(private val parentResolver: TypeResolver) : PgCryptoResolver(parentResolver) {
    override fun functionType(functionExpr: SqlFunctionExpr): IntermediateType? =
        when (functionExpr.functionName.text.lowercase()) {
            "bit_count" -> IntermediateType(PostgreSqlType.BIG_INT).asNullable()
            "decode" -> IntermediateType(BLOB).asNullable()
            "encode" -> IntermediateType(TEXT).asNullable()
            "gen_random_bytes" -> IntermediateType(BLOB).asNullable()
            "get_bit" -> IntermediateType(PostgreSqlType.INTEGER).asNullable()
            "get_byte" -> IntermediateType(PostgreSqlType.INTEGER).asNullable()
            "set_bit" -> IntermediateType(BLOB).asNullable()
            "set_byte" -> IntermediateType(BLOB).asNullable()
            "sha224" -> IntermediateType(BLOB).asNullable()
            "sha256" -> IntermediateType(BLOB).asNullable()
            "sha384" -> IntermediateType(BLOB).asNullable()
            "sha512" -> IntermediateType(BLOB).asNullable()
            else -> super.functionType(functionExpr)
        }
}
