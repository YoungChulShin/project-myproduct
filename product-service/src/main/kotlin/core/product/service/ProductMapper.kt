package core.product.service

import common.model.core.product.Product
import core.product.domain.ProductEntity
import org.mapstruct.*

@Mapper(
    componentModel = "Spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
)
interface ProductMapper {

    @Mappings(
        value = [
            Mapping(target = "serviceAddress", ignore = true),
        ]
    )
    fun entotyToApi(entity: ProductEntity): Product

    @Mappings(
        value = [
            Mapping(target = "id", ignore = true),
            Mapping(target = "version", ignore = true),
        ]
    )
    fun apiToEntity(api: Product): ProductEntity
}