package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Represents an entity of booking history within the context of carga sin estres booking system. This class is a JPA entity
 * that maps to the "bookings" table in the database.
 * <p>
 *     The annotations used in this class are:
 *     <ul>
 *         <li>{@code @Data}: This Lombok annotation generates getter and setter methods for the class attributes.</li>
 *         <li>{@code @Builder}: This Lombok annotation generates a constructor with all the class parameters.</li>
 *         <li>{@code @NoArgsConstructor}: This Lombok annotation generates an empty constructor.</li>
 *         <li>{@code @AllArgsConstructor}: This Lombok annotation generates a constructor with all the class parameters.</li>
 *         <li>{@code @Entity}: This JPA annotation indicates that the class is an entity.</li>
 *         <li>{@code @Table}: This JPA annotation indicates the name of the database table that represents the entity.</li>
 *     </ul>
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookingHistory")
public class BookingHistory {
    /**
     * The id of the booking history.
     * <p>
     *     This attribute maps to the "id" column in the "bookings" table in the database.
     *     The value is generated automatically.
     *     The column is a primary key.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The company of the booking history.
     * <p>
     *     This attribute maps to the "idCompany" column in the "bookings" table in the database.
     *     The column is a foreign key.
     *     The attribute is mapped by the {@code company} attribute of the {@code Company} class.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name="idCompany", nullable = false, foreignKey = @ForeignKey(name="FK_bookingHistory_company"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;

    /**
     * The client of the booking history.
     * <p>
     *     This attribute maps to the "idClient" column in the "bookings" table in the database.
     *     The column is a foreign key.
     *     The attribute is mapped by the {@code client} attribute of the {@code Client} class.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name="idClient", nullable = false, foreignKey = @ForeignKey(name="FK_bookingHistory_client"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    /**
     * The booking date of the booking history.
     */
    @Column(name="bookingDate", nullable = false)
    private LocalDate bookingDate;

    /**
     * The pickup address of the booking history.
     */
    @Column(name="pickupAddress", nullable = false)
    private String pickupAddress;

    /**
     * The destination address of the booking history.
     */
    @Column(name="destinationAddress", nullable = false)
    private String destinationAddress;

    /**
     * The moving date of the booking history.
     */
    @Column(name="movingDate", nullable = false)
    private Date movingDate;

    /**
     * The moving time of the booking history.
     * <p>
     *     The attribute is mapped by the {@code movingTime} attribute of the {@code Time} class.
     * </p>
     */
    @Temporal(TemporalType.TIME)
    @Column(name="movingTime", nullable = false)
    private String movingTime;

    /**
     * The status of the booking history.
     */
    @Column(name="status", nullable = false)
    private String status;

    /**
     * The services of the booking history.
     */
    @Column(name="Services", nullable = false)
    private String Services;

    /**
     * The payment of the booking history.
     */
    @Column(name="payment")
    private int payment;

    /**
     * The chats of the booking history.
     */
    @OneToMany(mappedBy = "bookingHistory", cascade = CascadeType.ALL)
    private List<Chat> chats;

}

