console.log("hwnd started!!!")

Vue.component('hwnd', {
    data: function () {
        return {
            count: 0,
            informers: [],

            styleObject: {
                border: 'blue 2px solid',
                borderRadius: '5px',
                margin: '5px auto',
                padding: '5px 5px',
                width: '100%',
                minWidth: '100px',
                height: '640px',
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
                'font-family': 'sans-serif',
                'text-align': 'center',
                // 'z-index': '8',
                'background-color':'transparent',
            }
        }
    },
    template:
        `<div v-bind:style = "styleObject" v-on:infoc = "procInformer($event)">
            <div v-bind:style = "editStyle">{{ title }}</div>
            
            <rbutton ref="rbutton1_hwnd1" title='Prod Add' bid = '10'></rbutton>
            <rbutton ref="rbutton2_hwnd1" title='Prod Rem' bid = '12'></rbutton>
            <rbutton ref="rbutton2_hwnd1" title='Prod Info' bid = '14'></rbutton>

            <informer 
                ref="informer0" 
                v-on:informer-click = "procInformer('click0')" 
                v-on:informer-create = "informerCreate" 
                title='Prod Info' 
                bid="0"
            />
            <informer 
                ref="informer1" 
                v-on:informer-click = "procInformer('click1')"
                v-on:informer-create = "informerCreate" 
                title='Prod Info' 
                bid="1"
            />
            <informer 
                ref="informer2" 
                v-on:informer-click = "procInformer('click2')"
                v-on:informer-create = "informerCreate" 
                title='Prod Info' 
                bid="2"
             />
            <informer 
                ref="informer3" 
                v-on:informer-click = "procInformer('click3')"
                v-on:informer-create = "informerCreate" 
                title='Prod Info' 
                bid="3"
             />
             
        </div>`,

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
        },

    methods: {
        dispatcher: function(stateObject){
            Console.log("dispatch ", stateObject.producer0);
        },
        controller: function(){
            Console.log("dispatch ", stateObject.producer0);
        },
        procInformer: function(ev){
            console.log('HWND informer emmited event processing in hwnd', ev);
        },
        informerCreate: function(ev){
            // console.log('HWND informer informerCreate', ev);
            this.informers[ev.bid]=ev.show;
            console.log('HWND informer informerCreate', this.informers);

        },
    },

});

