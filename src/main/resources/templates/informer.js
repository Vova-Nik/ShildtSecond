console.log("informer started!!!")
Vue.component('informer', {
    data: function () {
        return {
            count: 0,
            isOn: false,
            content: "It's content!",


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
            },
            indicatorStyle: {
                width: '20px',
                height: '20px',
                padding: '5px',
            },
            spotStyle:{
                border: '1px solid black',
                borderRadius: '50%',
                width: '14px',
                height: '14px',
                margin: '4px auto',
                backgroundColor: 'white',
                transition: '800ms',
                // cursor: 'pointer',
            },

            stateStyle:{
                width: '100%',
                height: '20px',
                borderLeft: '1px solid black',
                margin: '5px 5px 5px 0',
                padding: '5px',
            }
        }
    },

    template:
        `<div class="frame" v-bind:style = "frameStyle" v-on:click="$emit('informer-click')">
            <div class="indicator" v-bind:style = "indicatorStyle">
                    <div class="spot" v-bind:style = spotStyle v-on:click = "process"></div>
            </div>
           <div class="state" v-bind:style = "stateStyle">{{content}}</div>
        </div>`,

    props: {
        title: {
            type: String,
            default: 'spot',
        },
        bid: {
            type: String,
            default: '0',
        }
    },

    keeper: {},

    // beforeUpdate(){ },
    // updated() { },

    created: function () {
        console.log('informer'+ this.bid + ' - created');
        this.$emit('informer-create', {bid: this.bid, show: this });
    },

    methods: {

        clickk: function () {
            console.log('informer clic function  '+ 'informer' + this.bid + ' - click');
            this.$emit('informer-create');
        },

        process: function () {
            this.count++;
            console.log("spot clicked - ", this.count)
            if(this.isOn){
                this.spotStyle.backgroundColor = 'white';
                this.spotStyle.border = '1px solid black';
                this.isOn = false;
            }
            else{
                this.spotStyle.backgroundColor = 'red';
                this.spotStyle.border = '1px solid transparent';
                this.isOn = true;
            }
        },

        dimmer: function(){
            console.log(this.title + " dimmer");
            this.spotStyle.transition = '1500ms';
            this.spotStyle.backgroundColor = 'white';
            this.spotStyle.border = '1px solid black';
        },

        show: function(innerObj){
            if(!innerObj)
                return;
            this.spotStyle.transition = '1ms';
            this.spotStyle.backgroundColor = 'red';
            this.spotStyle.border = '1px solid transparent';
            console.log(this.title + " beforeUpdate");
            setTimeout(this.dimmer, 200);
             console.log("informer1.show", innerObj);
             this.content  = innerObj.class + "-" + innerObj.myNumber + ": made " + innerObj.produced + " items";

        },
}

});