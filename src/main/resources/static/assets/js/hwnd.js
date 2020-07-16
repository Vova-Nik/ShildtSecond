console.log("hwnd started!!!")

Vue.component('hwnd', {
    data: function () {
        return {
            count: 0,
            producerInformers: [],
            consumerInformers: [],
            qInformers: [],
            timerId: 2000,

            styleFrame:{
                display: "flex",
            },

            styleObject: {
                border: 'blue 2px solid',
                borderRadius: '5px',
                margin: '5px auto',
                padding: '5px 5px',
                width: '30%',
                minWidth: '100px',
                'font-family': 'sans-serif',
                'text-align': 'center',
                // 'z-index': '4',
                'background-color':'transparent',
            },
            editStyle: {
                border: 'black 1px solid',
                borderRadius: '5px',
                margin: '5px auto',
                padding: '5px 5px',
                width: '90%',
                height: '20px',
                minWidth: '100px',
                fontFamily: 'sans-serif',
                fontWeight: '800',
                //'text-align': 'center',
                // 'z-index': '8',
                'background-color':'transparent',
            }
        }
    },
    template:
    `
        <div v-bind:style = "styleFrame">
            <div v-bind:style = "styleObject">
                <div v-bind:style = "editStyle">Producers</div>
                <informer  ref="informer0" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="0" />
                <informer  ref="informer1" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="1" />
                <informer  ref="informer2" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="2" />
                <informer  ref="informer3" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="3" />
                <informer  ref="informer4" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="4" />
                <informer  ref="informer5" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="5" />
                <informer  ref="informer6" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="6" />
                <informer  ref="informer7" v-on:informer-create = "informerCreate" title='Producer' name="producerInformer" bid="7" />
            </div>

            <div v-bind:style = "styleObject">
                <div v-bind:style = "editStyle">Q</div>
                <informer  ref="q0" v-on:informer-create = "informerCreate" title='Consumer' name="qInformer" bid="0" />
            </div>

            <div v-bind:style = "styleObject">
                <div v-bind:style = "editStyle">Consumers</div>
                <informer  ref="informer10" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="0" />
                <informer  ref="informer11" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="1" />
                <informer  ref="informer12" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="2" />
                <informer  ref="informer13" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="3" />
                <informer  ref="informer14" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="4" />
                <informer  ref="informer15" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="5" />
                <informer  ref="informer16" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="6" />
                <informer  ref="informer17" v-on:informer-create = "informerCreate" title='Consumer' name="consumerInformer" bid="7" />
            </div>
        </div>        
        `,

        props: {
            title: {
                type: String,
                default: 'Watching',
              },
              bid: {
                type: String,
                default: '0',
              }
          },

        created: function(){
            console.log('hwnd created()');
            this.timerId = setInterval(this.tick, 2000);
        },

    methods: {
        informerCreate: function(context){
            console.log('HWND informer informerCreate', context);
            if(context.name =='consumerInformer')
                 {this.consumerInformers[context.bid] = context.redraw; return;}
            if(context.name == 'producerInformer')
                 {this.producerInformers[context.bid] = context.redraw; return;}
            if(context.name =='qInformer')
                   this.qInformers[context.bid] = context.redraw;
        },

        tick: function () {
           // console.log('tick()');
            this.request();
        },
        request: async function () {
            const origin = location.origin;
            let response = await fetch(
                'http://127.0.0.1:8081/message',
                //'http://192.168.0.2:8081/message',
                //'http://192.168.0.101:8081/message',
                //'http://192.168.0.100:8081/message',
                {
                    // method: 'post',
                    method: 'get',
                    headers: {
                        'Content-Type': 'application/json',
                        'Origin': origin,
                        'event': 'tick',
                        'btnClicked': 1,
                    },
                });
            state = await response.json();
            console.log("get - ", state);
            if (state.Producers.length < 1) {
                console.log("There are no producers");
                return;
            }
            let prodLength = this.producerInformers.length;
            // console.log('ttt this.producerInformers[]', this.producerInformers);
            for (let i = 0; i < prodLength; i++) {
                this.producerInformers[i](state.Producers[i]);
            }
            let consLength = this.consumerInformers.length;
            for (let i = 0; i < consLength; i++) {
                this.consumerInformers[i](state.Consumers[i]);
            }
            let qLength = this.qInformers.length;
            for (let i = 0; i < qLength; i++) {
                this.qInformers[i](state.Q[i]);
            }
        },
    },

});

        //  <rbutton ref="rbutton2_hwnd1" title='Add Prod' name="addProducerBtn" bid = '10'></rbutton>
