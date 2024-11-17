// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract ReservationContract {
    // Información de la reserva
    uint256 public reservationId;
    uint256 public registeredDate;
    uint256 public registeredTime;

    // Constructor que toma los datos de la reserva
    constructor(
        uint256 _reservationId,
        uint256 _registeredDate,
        uint256 _registeredTime
    ) {
        reservationId = _reservationId;
        registeredDate = _registeredDate;
        registeredTime = _registeredTime;
    }

    // Función para obtener la información completa de la reserva
    function getReservationDetails()
    public
    view
    returns (uint256, uint256, uint256)
    {
        return (reservationId, registeredDate, registeredTime);
    }
}
