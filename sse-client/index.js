$(function () {

    console.log("Application sucessfully loaded!");

    checkLocalStorageCapabilities();

    var $connectBtn = $('#connect');
    var $disconnectBtn = $('#disconnect').hide();

    var $connectStateBadge = $('#connect_state').hide();
    var $disconnectStateBadge = $('#disconnect_state');
    var $connectingStateBadge = $('#connecting_state').hide();

    var $broadcastCountBadge = $('#broadcast-count');
    var $eventdateCountBadge = $('#eventdate-count');

    var $broadcastCountBtn = $('#broadcast-count-btn');
    var $eventdateCountBtn = $('#eventdate-count-btn');


    var broadcastCount = 0;
    var eventDateCount = 0;

    var readyStateObservable;
    var subscription;


    var eventSource;

    $connectBtn.on('click', function () {
        connect();
    });

    $disconnectBtn.on('click', function () {
        disconnect();
    });

    function disconnect () {
        eventSource.close();

        $disconnectBtn.hide();
        $connectBtn.show();

        $connectStateBadge.hide();
        $disconnectStateBadge.show();

        subscription.unsubscribe();

        $broadcastCountBadge.html(broadcastCount);
        $broadcastCountBtn.removeClass('btn-success').addClass('btn-danger')

        $eventdateCountBadge.html(eventDateCount);
        $eventdateCountBtn.removeClass('btn-success').addClass('btn-danger')

        broadcastCount = 0;
        eventDateCount = 0;



    }

    function updateStateConnection (state) {

        $disconnectStateBadge.hide();
        $connectStateBadge.hide();
        $connectingStateBadge.hide();

        switch(state){
            case 1:$connectStateBadge.show();
                break;
            case 2:$disconnectStateBadge.show();
                break;
            case 0:$connectingStateBadge.show();
                break;
        }
    }




    function connect () {

        console.log("start....");

        eventSource = new EventSource('http://localhost:8080/stream?name=tutu');

        readyStateObservable  = Rx.Observable.interval(500);

        subscription = readyStateObservable.subscribe(function (connection) {
            console.log(eventSource.readyState);
            updateStateConnection(eventSource.readyState)
        });

        console.log(eventSource);

        eventSource.onopen = function(event) {

            console.log("connection open");

            $disconnectBtn.show();
            $connectBtn.hide();

            $connectStateBadge.show();
            $disconnectStateBadge.hide();

        };




        eventSource.onmessage = function(event) {
            console.log("onmessage! event:");
            console.log(event);

            const msg = JSON.parse(event.data);
            console.log(msg);

            $broadcastCountBadge.html(++broadcastCount);
            $broadcastCountBtn.addClass('btn-success').removeClass('btn-danger')

            console.log("origin:" + event.origin)

            $('#bre-id').html(event.lastEventId);
            $('#bre-type').html(event.type);
            $('#bre-origin').html(event.origin);

            $('#bre-date').html(msg.date);
            $('#bre-tick').html(msg.tick);
            $('#bre-event-id').html(msg.eventId);

        };

        eventSource.addEventListener('dateEvent', function(event) {

            console.log("onDateEvent !: ");
            console.log(event);


            const msg = JSON.parse(event.data);
            console.log(msg);

            $eventdateCountBadge.html(++eventDateCount);
            $eventdateCountBtn.addClass('btn-success').removeClass('btn-danger')

            $('#edt-id').html(event.lastEventId);
            $('#edt-type').html(event.type);
            $('#edt-origin').html(event.origin);

            $('#edt-date').html(msg.date);
            $('#edt-event-id').html(msg.eventId);
        }, false);
    }


    function checkLocalStorageCapabilities () {
        if(typeof localStorage!='undefined') {
            console.log('localStorage capabilities ok');
        }else{
            console.log('localStorage not supported');
        }
    }

});


