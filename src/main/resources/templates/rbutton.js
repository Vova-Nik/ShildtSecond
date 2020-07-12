console.log("rbutton started!!!")
Vue.component('rbutton', {
    data: function () {
        return {
            count: 0,

            styleObject: {
                border: 'black 1px solid',
                borderRadius: '5px',
                cursor: 'pointer',
                textAlign: 'center',
                margin: '5px auto',
                padding: '4px 5%',
                width: 'fit-content',
                minWidth: '16px',
                'z-index': '16',
                'font-family': 'sans-serif',
                boxShadow: '0px 0px 0px 0px rgba(0,0,0,0.75)',
                //position: 'absolute',
            }
        }
    },
    template:
        `<div
             v-on:click = "process" 
             v-bind:style = "styleObject"
             @mouseover = 'hoverOn'
             @mouseleave ='hoverOff'
             @mousedown = 'mousedown'
             @mouseup = 'mouseup'
        >
              {{ title }}
        </div>`,

    props: {
        title: {
            type: String,
            default: 'rBtn',
        },
        bid: {
            type: String,
            default: '0',
        }
    },

    methods: {
        process: function () {
            this.count++;
            console.log("rbutton - ", this.title, this.bid)

            this.request();
        },
        hoverOn: function () {
            this.styleObject.boxShadow = '0px 0px 2px 2px rgba(0,0,0,0.5)'
        },
        hoverOff: function () {
            this.styleObject.boxShadow = '0px 0px 0px 0px rgba(0,0,0,0.5)'
        },
        mousedown: function () {
            this.styleObject.boxShadow = '0px 0px 1px 1px rgba(0,0,0,0.5)'
        },
        mouseup: function () {
            this.styleObject.boxShadow = '0px 0px 2px 2px rgba(0,0,0,0.5)'
        },

        request: async function () {
            const origin = location.origin;
            let response = await fetch(
                 'http://127.0.0.1:8081/message',
                // 'http://192.168.0.101:8081/message',
                //'http://192.168.0.100:8081/message',

                {
                    method: 'post',
                    headers: { 
                        'Content-Type': 'application/json',
                        'Origin': origin,
                        'btnClicked': 1,
                        'event': 'restBtnClicked',
                        },
                    body: JSON.stringify({
                        "btnId": this.bid,
                    })
                });
            // console.log("response is ", response);
            rasp = await response.json();
            console.log("response 2nd phase is ", rasp);
        }
    },

});

