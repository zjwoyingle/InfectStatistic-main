import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * InfectStatistic
 * TODO
 *
 * @author zhangping
 * @version 2.0
 */
class InfectStatistic
{
    public static void main(String[] args)
    {
    	//����ʡ�������飨����ȫ����
    	String province[]={"ȫ��","����","����","����","����","����","�㶫","����",
    			"����","����","�ӱ�","����","������","����","����","����","����",
    			"����","����","���ɹ�","����","�ຣ","ɽ��","ɽ��","����","�Ϻ�",
    			"�Ĵ�","���","����","�½�","����","�㽭"};
    	String type[]={"ip","sp","cure","dead"};
    	//��¼��ʡ������ȫ�����������͵��˵�����,���һλ��¼��ʡ�Ƿ��漰��
    	int[][] number=new int[32][5];
    	//������Ÿ����������в�����ArrayList
    	String logList=new String();
		String outList=new String();
		String dateList=new String();
		ArrayList<String> typeList=new ArrayList<String>();
		ArrayList<Integer> typeInt=new ArrayList<Integer>();
		ArrayList<String> provinceList=new ArrayList<String>();
		ArrayList<Integer> provinceInt=new ArrayList<Integer>();
		
		//��ʼ����ά����number
    	for (int i=0;i<32;i++)
    	{
    		for (int j=0;j<5;j++)
    		{
    			number[i][j]=0;
    		}
    	}
    	//�õ���Ÿ����������в�����ArrayList����ֵ
    	for (int i=0;i<args.length;i++)
    	{
    		if (args[i].equals("-log")) {
    			logList=args[++i];
    			//System.out.println(logList);
    			logList=logList.replace('/', '\\');
    			//System.out.println(logList);
    		}
    		else if (args[i].equals("-out")) {
    			outList=args[++i];
    			outList=outList.replace('/', '\\');
    		}
    		else if (args[i].equals("-date")) {
    			dateList=args[++i];
    		}
    		else if (args[i].equals("-type")) {
    			for (int j=i+1;j<args.length;j++) {
    				if (!args[j].equals("-out")&&!args[j].equals("-date")&&
    					!args[j].equals("-log")&&!args[j].equals("-province"))
    				{
    					typeList.add(args[j]);
    				}
    				else 
    				{
    					i=j-1;
    					break;
    				}
    			}
    		}
    		else if (args[i].equals("-province")) {
    			for (int j=i+1;j<args.length;j++) {
    				if (!args[j].equals("-out")&&!args[j].equals("-date")&&
    					!args[j].equals("-type")&&!args[j].equals("-log"))
    				{
    					provinceList.add(args[j]);
    				}
    				else 
    				{
    					i=j-1;
    					break;
    				}
    			}
    		}
		}//��ȡArrayListѭ������
    	//��ȡʡ�ݡ����Ͷ�Ӧ���±�
    	for (int i=0;i<typeList.size();i++)
    	{
    		for (int j=0;j<type.length;j++)
    		{
    			if (typeList.get(i).equals(type[j])) 
    			{
    				typeInt.add(j);
    			}
    		}
    	}
    	for (int i=0;i<provinceList.size();i++)
    	{
    		for (int j=0;j<province.length;j++)
    		{
    			if (provinceList.get(i).equals(province[j])) 
    			{
    				provinceInt.add(j);
    			}
    		}
    	}
    	
    	File file=new File(logList);
        //��ȡ��·���µ��ļ����ļ���
        String[] arr=file.list();
        //������־�е�����
        for(String s:arr)
        {
        	if (s.compareTo(dateList+".log")>0)
        	{
        		continue;
        	}
			try 
			{			    
				File afile = new File(logList+s);
		        Scanner sc = new Scanner(afile);
		        while (sc.hasNext()) 
		        {
		            String first=sc.next();
		            if (first.equals("//")) 
		            {
		            	sc.nextLine();
		            } 
		            else 
		            {
		            	int index=0;
		            	for (int i=0;i<32;i++)
		            	{
		            		if (first.equals(province[i]))
		            		{
		            			index=i;
		            			break;
		            		}
		            	}
		            	//System.out.println(index);
		            	number[0][4]=1;
		            	number[index][4]=1;
		            	String second=sc.next();
		                if (second.equals("����")) 
		                {
		                	String third=sc.next();
		                	String four=sc.next();
		                	four=four.replace("��", "");
		                	int member=Integer.parseInt(four);	                	
		                	if (third.equals("��Ⱦ����"))
		                	{
		                		number[0][0]+=member;
		                		number[index][0]+=member;
		                	}
		                	else
		                	{
		                		number[0][1]+=member;
		                		number[index][1]+=member;
		                	}
		                }
		                else if (second.equals("��Ⱦ����"))
		                {
		                	sc.next();
		                	String four=sc.next();
		                	String five=sc.next();
		                	five=five.replace("��", "");
		                	int member=Integer.parseInt(five);
		                	int index1=0;
		                	for (int i=0;i<32;i++)
			            	{
			            		if (four.equals(province[i]))
			            		{
			            			index1=i;
			            			break;
			            		}
			            	}
		                	number[index][0]-=member;
		                	number[index1][0]+=member;
		                	number[index1][4]=1;
		                }
		                else if (second.equals("���ƻ���"))
		                {
		                	String three=sc.next();
		                	if (three.equals("����"))
		                	{
		                		String four=sc.next();
			                	String five=sc.next();
			                	five=five.replace("��", "");
			                	int member=Integer.parseInt(five);
			                	int index1=0;
			                	for (int i=0;i<32;i++)
				            	{
				            		if (four.equals(province[i]))
				            		{
				            			index1=i;
				            			break;
				            		}
				            	}
			                	number[index][1]-=member;
			                	number[index1][1]+=member;
			                	number[index1][4]=1;
		                	}
		                	else
		                	{
		                		String four=sc.next();
		                		four=four.replace("��", "");
			                	int member=Integer.parseInt(four);
			                	number[index][0]+=member;
			                	number[index][1]-=member;
			                	number[0][0]+=member;
			                	number[0][1]-=member;
		                	}
		                }
		                else if (second.equals("����"))
		                {
		                	String three=sc.next();
		                	three=three.replace("��", "");
		                	int member=Integer.parseInt(three);
		                	number[index][3]+=member;
		                	number[index][0]-=member;
		                	number[0][3]+=member;
		                	number[0][0]-=member;
		                }
		                else if (second.equals("����"))
		                {
		                	String three=sc.next();
		                	three=three.replace("��", "");
		                	int member=Integer.parseInt(three);
		                	number[index][2]+=member;
		                	number[index][0]-=member;
		                	number[0][2]+=member;
		                	number[0][0]-=member;
		                }
		                else if (second.equals("�ų�"))
		                {
		                	sc.next();
		                	String four=sc.next();
	                		four=four.replace("��", "");
		                	int member=Integer.parseInt(four);
		                	number[index][1]-=member;
		                	number[0][1]-=member;
		                }
		            }	                	
		        }
		        sc.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}           
        }
        
    	//�õ�������
        ArrayList<String> result=new ArrayList<String>();
        if (provinceInt.size()==0)
        {
        	if (typeInt.size()==0)
        	{
        		for (int i=0;i<32;i++)
            	{
        			if (number[i][4]==0)
        			{
        				continue;
        			}
        			String mid=province[i]+" ��Ⱦ����"+""+number[i][0]+""+"��"+" ���ƻ���"+""+number[i][1]+""+"��"
        			+" ����"+""+number[i][2]+""+"��"+" ����"+""+number[i][3]+""+"��\n";
        			result.add(mid);
            	}
        	}
        	else
        	{
        		for (int i=0;i<32;i++)
            	{
        			if (number[i][4]==0)
        			{
        				continue;
        			}
        			String mid=province[i];
        			for (int j=0;j<typeInt.size();j++)
        			{
        				if (typeInt.get(j)==0)
        				{
        					mid=mid+" ��Ⱦ����"+""+number[i][0]+""+"��";
        				}
        				else if (typeInt.get(j)==1)
        				{
        					mid=mid+" ���ƻ���"+""+number[i][1]+""+"��";
        				}
        				else if (typeInt.get(j)==2)
        				{
        					mid=mid+" ����"+""+number[i][2]+""+"��";
        				}
        				else if (typeInt.get(j)==3)
        				{
        					mid=mid+" ����"+""+number[i][3]+""+"��";
        				}
        			}
        			mid+="\n";
        			result.add(mid);
            	}
        	}
        }
        else
        {
        	for (int i=0;i<32;i++)
        	{
        		number[i][4]=0;
        	}
        	for (int i=0;i<provinceInt.size();i++)
        	{
        		number[provinceInt.get(i)][4]=1;
        	}
        	if(typeInt.size()==0)
        	{
        		for (int i=0;i<32;i++)
            	{
        			if (number[i][4]==0)
        			{
        				continue;
        			}
        			String mid=province[i]+" ��Ⱦ����"+""+number[i][0]+""+"��"+" ���ƻ���"+""+number[i][1]+""+"��"
        			+" ����"+""+number[i][2]+""+"��"+" ����"+""+number[i][3]+""+"��\n";
        			result.add(mid);
            	}
        	}
        	else
        	{
        		for (int i=0;i<32;i++)
            	{
        			if (number[i][4]==0)
        			{
        				continue;
        			}
        			String mid=province[i];
        			for (int j=0;j<typeInt.size();j++)
        			{
        				if (typeInt.get(j)==0)
        				{
        					mid=mid+" ��Ⱦ����"+""+number[i][0]+""+"��";
        				}
        				else if (typeInt.get(j)==1)
        				{
        					mid=mid+" ���ƻ���"+""+number[i][1]+""+"��";
        				}
        				else if (typeInt.get(j)==2)
        				{
        					mid=mid+" ����"+""+number[i][2]+""+"��";
        				}
        				else if (typeInt.get(j)==3)
        				{
        					mid=mid+" ����"+""+number[i][3]+""+"��";
        				}
        			}
        			mid+="\n";
        			result.add(mid);
            	}
        	}
        }
        
        //��������ָ���ļ���
        try {
	        FileOutputStream out = new FileOutputStream(outList);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
	        for (int i=0;i<result.size();i++) {
	        	bw.write(result.get(i));
	        }
	        bw.write("// ���ĵ�������ʵ���ݣ���������ʹ��\n");
	        bw.close();
        }
        catch (Exception e) 
		{
			e.printStackTrace();
		}   
        
    	/*//test
    	System.out.println(logList);
    	System.out.println(outList);
    	System.out.println(dateList);
    	for (int i=0;i<typeInt.size();i++)
    		System.out.print(typeInt.get(i));
    	System.out.println();
    	for (int i=0;i<provinceInt.size();i++)
    		System.out.print(provinceInt.get(i));
    	System.out.println();
    	
    	for (int i=0;i<32;i++)
    	{
    		for (int j=0;j<4;j++)
    		{
    			if(number[i][j]!=0) {
    			System.out.print(i);
    			System.out.print(" ");
    			System.out.print(j);
    			System.out.print(" ");
    			System.out.print(number[i][j]);
    			System.out.println();
    			} 			
    		}
    	}
    	*/
    }
}