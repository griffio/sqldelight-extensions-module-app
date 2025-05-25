package griffio

import app.cash.sqldelight.dialect.api.IntermediateType
import app.cash.sqldelight.dialect.api.PrimitiveType.BLOB
import app.cash.sqldelight.dialect.api.PrimitiveType.TEXT
import app.cash.sqldelight.dialect.api.SqlDelightModule
import app.cash.sqldelight.dialect.api.TypeResolver
import com.alecstrong.sql.psi.core.psi.SqlFunctionExpr

class PgCryptoModule : SqlDelightModule {
    override fun typeResolver(parentResolver: TypeResolver): TypeResolver = PgCryptoResolver(parentResolver)

    override fun setup() {
    }
}

open class PgCryptoResolver(private val parentResolver: TypeResolver) : Bm25TypeResolver(parentResolver) {

    override fun functionType(functionExpr: SqlFunctionExpr): IntermediateType? =
        when (functionExpr.functionName.text.lowercase()) {
            "armor" -> IntermediateType(TEXT).asNullable()
            "crypt" -> IntermediateType(TEXT).asNullable()
            "dearmor" -> IntermediateType(BLOB).asNullable()
            "decrypt" -> IntermediateType(BLOB).asNullable()
            "decrypt_iv" -> IntermediateType(BLOB).asNullable()
            "digest" -> IntermediateType(BLOB).asNullable()
            "encrypt" -> IntermediateType(BLOB).asNullable()
            "encrypt_iv" -> IntermediateType(TEXT).asNullable()
            "gen_random_bytes" -> IntermediateType(BLOB).asNullable()
            "gen_salt" -> IntermediateType(TEXT).asNullable()
            "hmac" -> IntermediateType(BLOB).asNullable()
            "pgp_key_id" -> IntermediateType(TEXT).asNullable()
            "pgp_pub_decrypt" -> IntermediateType(TEXT).asNullable()
            "pgp_pub_decrypt_bytea" -> IntermediateType(BLOB).asNullable()
            "pgp_pub_encrypt" -> IntermediateType(BLOB).asNullable()
            "pgp_pub_encrypt_bytea" -> IntermediateType(BLOB).asNullable()
            "pgp_sym_decrypt" -> IntermediateType(TEXT).asNullable()
            "pgp_sym_decrypt_bytea" -> IntermediateType(BLOB).asNullable()
            "pgp_sym_encrypt" -> IntermediateType(BLOB).asNullable()
            "pgp_sym_encrypt_bytea" -> IntermediateType(BLOB).asNullable()
            else -> super.functionType(functionExpr)
        }
}
