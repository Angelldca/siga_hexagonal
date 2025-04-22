package com.angelldca.siga.infrastructure.adapter.out.persistence.specification;

import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.criteria.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

public class GenericSpecification <T> implements Specification<T> {
    private final SearchCriteria criteria;

    public GenericSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String[] keys = criteria.getKey().split("\\.");
        Path<Object> path = root.get(keys[0]);

        if (criteria.getKey().contains(".")) {
            path = root.get(keys[0]);
            for (int i = 1; i < keys.length; i++) {
                path = path.get(keys[i]);
            }
        } else {
            path = root.get(criteria.getKey());
        }

        // Verificar si el valor es un UUID y convertirlo a UUID si es necesario.
        Object value = criteria.getValue();
        System.out.println("Tipo de dato: " + value.getClass().getName());

        Class<?> fieldType = path.getJavaType();
        System.out.println("Tipo de dato Path: " + fieldType);
        if (value instanceof String) {
            String val = (String) value;

            if (fieldType.equals(UUID.class) && isValidUUID(val)) {
                value = UUID.fromString(val);
            } else if (fieldType.equals(Long.class)) {
                value = Long.parseLong(val);
            } else if (fieldType.equals(Integer.class)) {
                value = Integer.parseInt(val);
            } else if (fieldType.equals(Float.class)) {
                value = Float.parseFloat(val);
            } else if (fieldType.equals(Double.class)) {
                value = Double.parseDouble(val);
            } else if (fieldType.equals(BigDecimal.class)) {
                value = new BigDecimal(val);
            } else if (fieldType.equals(Boolean.class)) {
                value = Boolean.parseBoolean(val.toLowerCase());
            }else if (fieldType.equals(LocalDate.class)) {
                // Ejemplo: "2025-04-11"
                DateTimeFormatter DateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
                value = LocalDate.parse(val, DateFormatter);

            } else if (fieldType.equals(LocalDateTime.class)) {
                // Puede venir con offset: "2025-04-11T12:00:00-05:00"

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                value = LocalDateTime
                        .parse(val, dateTimeFormatter);

            } else if (fieldType.equals(LocalTime.class)) {
                DateTimeFormatter TimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
                value = LocalTime
                        .parse(val, TimeFormatter);
            } else {
                value = val.toLowerCase();
            }
        }
        /*else {
                // Fechas
                DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                DateTimeFormatter TimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
                // Intentar convertir el valor a LocalDate o LocalDateTime si es necesario.
                try {
                    value = LocalDate.parse(value.toString(), dateFormatter);
                    System.out.println("Se convirtio a fecha");
                } catch (DateTimeParseException ignored) {
                    try {
                        value = LocalDateTime.parse(value.toString(), dateTimeFormatter);
                        System.out.println("Se convirtio a hora y fecha");
                    } catch (DateTimeParseException ignored2) {
                        value = LocalTime.parse(value.toString(), TimeFormatter);
                        System.out.println("Se convirtio a hora");
                    }
                }
            }*/



        return switch (criteria.getOperation()) {
            case LIKE ->  {
                if (value instanceof UUID) {
                    yield builder.equal(path.as(UUID.class), (UUID) value);
                }else if (!(value instanceof String)) {
                    yield builder.equal(path.as(fieldType),value);
                } else {
                    yield builder.like(
                            builder.lower(path.as(String.class)),
                            "%" + value.toString().toLowerCase() + "%"
                    );
                }
            }
            case EQUALS -> {
                if (value instanceof UUID) {
                    yield builder.equal(path.as(UUID.class), (UUID) value);
                }else if (!(value instanceof String)) {
                    yield builder.equal(path.as(fieldType),value);
                } else {
                    yield builder.equal(
                            builder.lower(builder.function("replace", String.class, path.as(String.class), builder.literal(" "), builder.literal(""))),
                            value.toString().toLowerCase().replace(" ", "")
                    );
                }
            }
            case NOT_EQUALS -> {
                if (value instanceof LocalDate) {
                    yield builder.notEqual(path.as(LocalDate.class), (LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    yield builder.notEqual(path.as(LocalDateTime.class), (LocalDateTime) value);
                } else {
                    yield builder.notEqual(
                            builder.lower(builder.function("replace", String.class, path.as(String.class), builder.literal(" "), builder.literal(""))),
                            value.toString().toLowerCase().replace(" ", "")
                    );
                }
            }

            case GREATER_THAN -> {
                if (value instanceof LocalDate) {
                    yield builder.greaterThan(path.as(LocalDate.class), (LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    yield builder.greaterThan(path.as(LocalDateTime.class), (LocalDateTime) value);
                } else if (value instanceof Integer) {
                    yield builder.greaterThan(path.as(Integer.class), (Integer) value);
                }
                else if (value instanceof Double) {
                    yield builder.greaterThan(path.as(Double.class), (Double) value);
                }
                else {
                    yield builder.greaterThan(path.as(String.class), value.toString());
                }
            }
            case LESS_THAN -> {
                if (value instanceof LocalDate) {
                    yield builder.lessThan(path.as(LocalDate.class), (LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    yield builder.lessThan(path.as(LocalDateTime.class), (LocalDateTime) value);
                } else if (value instanceof Integer) {
                    yield builder.lessThan(path.as(Integer.class), (Integer) value);
                }
                else if (value instanceof Double) {
                    yield builder.lessThan(path.as(Double.class), (Double) value);
                }
                else {
                    yield builder.lessThan(path.as(String.class), value.toString());
                }
            }
            case GREATER_THAN_OR_EQUAL_TO -> {
                if (value instanceof LocalDate) {
                    yield builder.greaterThanOrEqualTo(path.as(LocalDate.class), (LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    yield builder.greaterThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) value);
                } else if (value instanceof Integer) {
                    yield builder.greaterThanOrEqualTo(path.as(Integer.class), (Integer) value);
                }
                else if (value instanceof Double) {
                    yield builder.greaterThanOrEqualTo(path.as(Double.class), (Double) value);
                } else {
                    yield builder.greaterThanOrEqualTo(path.as(String.class), value.toString());
                }
            }
            case LESS_THAN_OR_EQUAL_TO -> {
                if (value instanceof LocalDate) {
                    yield builder.lessThanOrEqualTo(path.as(LocalDate.class), (LocalDate) value);
                } else if (value instanceof LocalDateTime) {
                    yield builder.lessThanOrEqualTo(path.as(LocalDateTime.class), (LocalDateTime) value);
                } else if (value instanceof Integer) {
                    yield builder.lessThanOrEqualTo(path.as(Integer.class), (Integer) value);
                } else if (value instanceof Double) {
                    yield builder.lessThanOrEqualTo(path.as(Double.class), (Double) value);
                }
                else {
                    yield builder.lessThanOrEqualTo(path.as(String.class), value.toString());
                }
            }
            case IN -> {
                CriteriaBuilder.In<Object> inClause = builder.in(path);
                if (value instanceof List) {
                    for (Object item : (List<?>) value) {
                        Object finalValue = convertToUUID(item.toString());
                        if (finalValue == null) {
                            finalValue = item.toString();
                        }
                        inClause.value(finalValue);
                    }
                } else {
                    Object finalValue = convertToUUID(value.toString());
                    if (finalValue == null) {
                        finalValue = value.toString();
                    }
                    inClause.value(finalValue);
                }
                yield inClause;
            }
            case NOT_IN -> {
                CriteriaBuilder.In<Object> inClause = builder.in(path);
                if (value instanceof List) {
                    for (Object item : (List<?>) value) {
                        Object finalValue = convertToUUID(item.toString());
                        if (finalValue == null) {
                            finalValue = item.toString();
                        }
                        inClause.value(finalValue);
                    }
                } else {
                    Object finalValue = convertToUUID(value.toString());
                    if (finalValue == null) {
                        finalValue = value.toString();
                    }
                    inClause.value(finalValue);
                }
                // Usamos builder.not para negar la cláusula "IN"
                yield builder.not(inClause);
            }
            case IS_NULL -> builder.isNull(path);
            case IS_NOT_NULL -> builder.isNotNull(path);
            case IS_TRUE -> builder.isTrue(path.as(Boolean.class));
            case IS_FALSE -> builder.isFalse(path.as(Boolean.class));
            case EXISTS -> {
                Join<T, ?> join = root.join(criteria.getKey(), JoinType.LEFT);
                yield builder.isNotNull(join.get("id"));
            }
            case NOT_EXISTS -> {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<T> subRoot = (Root<T>) subquery.from(root.getJavaType());
                subquery.select(builder.count(subRoot))
                        .where(builder.equal(subRoot.get(criteria.getKey()).get("id"), root.get("id")));
                yield builder.not(builder.exists(subquery));
            }
            default -> throw new IllegalArgumentException("Operación no soportada: " + criteria.getOperation());
        };
    }

    // Método auxiliar para verificar si un String es un UUID válido
    private boolean isValidUUID(String str) {
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    private boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Método auxiliar para convertir un String a UUID
    private UUID convertToUUID(String str) {
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
