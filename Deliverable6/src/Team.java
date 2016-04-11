
/**
 * The team class is built for each individual team in the tournament. Each team has a name, region, and more values associated 
 * with it. Additionally, weights that are applied to these metrics are also have set and get methods for game calculations.
 */
public class Team{
		
		String name; String region; int rpi; int bpi; int adjO; int adjD; int luck;
		double rpiW; double bpiW; double adjOW; double adjDW; double luckW;
		
		public Team(String teamName){
			this.name = teamName;
		}
		
		public void setRegion(String r){
			this.region = r;
		}
		
		public void setRpi(int rpi){
			this.rpi = rpi;
		}
		public void setBpi(int bpi){
			this.bpi = bpi;
		}
		public void setAdjO(int adjO){
			this.adjO = adjO;
		}
		public void setAdjD(int adjD){
			this.adjD = adjD;
		}
		public void setLuck(int luck){
			this.luck = luck;
		}
		
		public void setrpiWeight(double r){
			this.rpiW = r;
		}

		public void setbpiWeight(double b){
			this.bpiW = b;
		}

		public void setadjOWeight(double o){
			this.adjOW = o;
		}

		public void setadjDWeight(double d){
			this.adjDW = d;
		}

		public void setLuckWeight(double l){
			this.luckW = l;
		}
	}
