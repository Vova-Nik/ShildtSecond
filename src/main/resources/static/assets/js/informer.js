console.log("informer started!!!")
Vue.component('informer', {
    data: function () {
        return {
            count: 0,
            isOn: false,
            content: "It's content!",
            contentOld: "",

            frameStyle: {
                border: '1px solid black',
                borderRadius: '5px',
                display: 'flex',
                flexDirection: 'row',
                justifyContent: 'center',
                alignItems: 'center',
                width: '100%',
                margin: '5px auto',
                fontFamily: 'Arial, Helvetica, sans-serif',
                fontSize: '12px',
                cursor: 'pointer',
                textAlign: 'left',
                backgroundColor: 'tomato',
            },
            indicatorStyle: {
                width: '20px',
                padding: '5px',
            },
            spotStyle: {
                border: '1px solid black',
                borderRadius: '50%',
                width: '14px',
                height: '14px',
                margin: '4px auto',
                backgroundColor: 'white',
                transition: '800ms',
            },

            stateStyle: {
                width: '100%',
                //height: '20px',
                borderLeft: '1px solid black',
                margin: '5px 5px 5px 0',
                padding: '2px 5px',
            }
        }
    },

    template:
        `<div class="frame" v-bind:style = "frameStyle" v-on:click="click">
            <div class="indicator" v-bind:style = "indicatorStyle">
                    <div class="spot" v-bind:style = spotStyle v-on:click = "process"></div>
            </div>
           <div class="state" v-bind:style = "stateStyle" v-html="content"></div>
        </div>`,
    // {{content}}
    props: {
        title: {
            type: String,
            default: 'spot',
        },
        bid: {
            type: String,
            default: '0',
        },
        name: {
            type: String,
            default: 'rButton',
        },
    },

    // beforeUpdate(){ },
    // updated: function(){},

    created: function () {
        console.log('informer' + this.bid + ' - created');
        this.$emit('informer-create', this);
    },

    methods: {
        process: function () {
            this.count++;
            console.log("spot clicked - ", this.count)
            if (this.isOn) {
                this.spotStyle.backgroundColor = 'white';
                this.spotStyle.border = '1px solid black';
                this.isOn = false;
            }
            else {
                this.spotStyle.backgroundColor = 'red';
                this.spotStyle.border = '1px solid transparent';
                this.isOn = true;
            }
        },

        dimmer: function () {
            // console.log(this.title + " dimmer");
            this.spotStyle.transition = '1500ms';
            this.spotStyle.backgroundColor = 'white';
            this.spotStyle.border = '1px solid black';
        },

        flash: function (color = 'red') {
            this.spotStyle.transition = '1ms';
            this.spotStyle.backgroundColor = color;
            this.spotStyle.border = '1px solid transparent';
            setTimeout(this.dimmer, 200);
        },

        redraw: function (innerObj) {
            if (!innerObj)
                return;
            // console.log("Redrow innerObject keys = ", innerObj.inWork);
            if (innerObj.inWork=='false')
                this.frameStyle.backgroundColor = 'white';
            if (innerObj.inWork=='true')
                this.frameStyle.backgroundColor = '#ccffcc';


            innerObgKeys = Object.keys(innerObj);
            tcontent = "";

            for (kk in innerObj) {
                tcontent += kk + ":" + innerObj[kk] + ", ";
            }

            //tcontent = "Producer:" + innerObj.myNumber + ",<br> Interval:" + innerObj.timeInterval/1000 + "c" + " <br>Made " + innerObj.produced + " items";
            if (tcontent == this.content)
                return;
            this.content = tcontent;
            this.flash();
        },

        click: function () {
            this.flash('green');
            //console.log('informer clic function--------------------  '+ 'informer ' + " title:" + this.title + " bid:" + this.bid);
            this.btnRequest();
        },

        btnRequest: async function (owner, btnId) {
            const origin = location.origin;
            let response = await fetch(
                'http://127.0.0.1:8081/message',
                //'http://192.168.0.2:8081/message',
                //'http://192.168.0.100:8081/message',

                {
                    method: 'post',
                    headers: {
                        'Content-Type': 'application/json',
                        'Origin': origin,
                        'event': 'informerClicked',
                    },
                    body: JSON.stringify({
                        "elementNumber": this.bid,
                        "elementName": this.name,
                    })
                });
            // console.log("response is ", response);
            rasp = await response.json();
            console.log("response 2nd phase is ", rasp);
        },
    }

});