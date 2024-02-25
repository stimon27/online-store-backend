package org.rimini.hibernate.orm.panache.rest.cms;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rimini.hibernate.orm.panache.entities.store.product.Product;
import org.rimini.hibernate.orm.panache.entities.store.product.ProductCategory;
import org.rimini.hibernate.orm.panache.entities.store.product.ProductSubcategory;
import org.rimini.hibernate.orm.panache.entities.store.product.ProductCreateRequest;
import org.rimini.hibernate.orm.panache.entities.store.product.ProductUpdateRequest;
import org.rimini.hibernate.orm.panache.entities.store.stock.StockData;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class ProductResource {
    private static final String PRODUCT_KEY = "product";
    private static final String CATEGORY_KEY = "category";
    private static final String SUBCATEGORY_KEY = "subcategory";
    private static final String STOCK_DATA_KEY = "stockData";
    @GET
    public List<Product> list() {
        return Product.listAll();
    }

    @GET
    @Path("/{id}")
    public Product get(Long id) {
        return Product.findById(id);
    }

    @POST
    @Transactional
    public Response create(ProductCreateRequest request) {
        Map<String, PanacheEntity> entityMap
                = ProductResource.mapCreateRequestToEntities(request);
        PanacheEntity.persist(List.of(
                entityMap.get(CATEGORY_KEY),
                entityMap.get(SUBCATEGORY_KEY),
                entityMap.get(PRODUCT_KEY),
                entityMap.get(STOCK_DATA_KEY)
        ));
        Long productId = entityMap.get(PRODUCT_KEY).id;
        return Response.created(URI.create("/products/" + productId)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(Long id, ProductUpdateRequest request) {
        Map<String, PanacheEntity> entityMap
                = ProductResource.mapUpdateRequestToEntities(id, request);
        PanacheEntity.persist(entityMap.get(PRODUCT_KEY),
                entityMap.get(STOCK_DATA_KEY));
        Product product = (Product) entityMap.get(PRODUCT_KEY);
        return Response.ok(product).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(Long id) {
        Product product = Product.findById(id);
        if (product == null) {
            throw new NotFoundException();
        }
        product.delete();
        return Response.ok().build();
    }

    private static Map<String, PanacheEntity> mapCreateRequestToEntities(
            final ProductCreateRequest request
    ) {
        StockData stockData = StockData.builder()
                .quantity(request.quantity())
                .refillIncoming(false)
                .build();

        ProductCategory category = ProductCategory.builder()
                .name(request.categoryName())
                .build();

        ProductSubcategory subcategory = ProductSubcategory.builder()
                .name(request.subcategoryName())
                .category(category)
                .build();

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .variantCode(request.variantCode())
                .color(request.color())
                .price(request.price())
                .stockData(stockData)
                .subcategory(subcategory)
                .build();

        product.stockData.product = product;

        return Map.of(
                STOCK_DATA_KEY, stockData,
                CATEGORY_KEY, category,
                SUBCATEGORY_KEY, subcategory,
                PRODUCT_KEY, product
        );
    }

    private static Map<String, PanacheEntity> mapUpdateRequestToEntities(
            final Long id,
            final ProductUpdateRequest request
    ) {
        Product product = Product.findById(id);
        if (product == null) {
            throw new NotFoundException();
        }
        product.name = request.name();
        product.description = request.description();
        product.variantCode = request.variantCode();
        product.color = request.color();
        product.price = request.price();

        StockData stockData = product.stockData;
        stockData.quantity = request.quantity();

        return Map.of(
                PRODUCT_KEY, product,
                STOCK_DATA_KEY, stockData
        );

    }
}
